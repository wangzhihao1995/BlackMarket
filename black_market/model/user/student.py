import pickle
import requests
from task.celery import app
from datetime import datetime

from black_market.ext import db
from black_market.libs.cache.redis import mc, rd, ONE_HOUR, ONE_DAY, ONE_WEEK
from black_market.model.wechat.user import WechatUser
from black_market.model.utils import validator
from black_market.model.user.consts import AccountStatus
from black_market.model.exceptions import MobileAlreadyExistedError
from black_market.model.exceptions import (
    WechatUserNotFoundError, CannotViewPostContactError)


class Student(db.Model):
    __tablename__ = 'student'

    id = db.Column(db.Integer, db.ForeignKey('wechat_user.id'))
    name = db.Column(db.String(80))
    mobile = db.Column(db.String(80), primary_key=True, index=True)
    open_id = db.Column(db.String(80), db.ForeignKey('wechat_user.open_id'))
    type = db.Column(db.SmallInteger)
    grade = db.Column(db.String(10))
    status = db.Column(db.SmallInteger, default=AccountStatus.need_verify.value)
    create_time = db.Column(db.DateTime, default=datetime.now)
    update_time = db.Column(db.DateTime, default=datetime.now)
    MAX_VIEWCOUNT = 12

    _cache_key_prefix = 'student:'
    _student_cache_key = _cache_key_prefix + 'id:%s'
    _avatar_cache_key = _cache_key_prefix + 'avatar:%s'
    _remaining_viewcount_by_student_cache_key = _cache_key_prefix + 'remaining:viewcount:id:%s'

    def __init__(self, id_, mobile, open_id, type_, grade, status):
        self.id = id_
        self.name = ''
        self.mobile = mobile
        self.open_id = open_id
        self.type = type_.value
        self.grade = grade
        self.status = status.value

    def dump(self):
        return dict(
            id=self.id, username=self.username, mobile=self.mobile,
            grade=self.grade, type=self.type, status=self.status,
            avatar_url=self.avatar_url,
            create_time=self.create_time, update_time=self.update_time)

    def share_dump(self):
        return dict(
            id=self.id, username=self.username, grade=self.grade,
            type=self.type, avatar_url=self.avatar_url, create_time=self.create_time)

    @classmethod
    def add(cls, id_, mobile, open_id, type_, grade, status=AccountStatus.need_verify):
        wechat_user = WechatUser.get_by_open_id(open_id)
        if wechat_user is None:
            raise WechatUserNotFoundError()
        if Student.existed(mobile):
            raise MobileAlreadyExistedError()
        student = Student(id_, mobile, open_id, type_, grade, status)
        db.session.add(student)
        db.session.commit()
        return student.id

    @classmethod
    def get(cls, id_):
        cache_key = cls._student_cache_key % id_
        if mc.get(cache_key):
            return pickle.loads(bytes.fromhex(mc.get(cache_key)))
        student = cls.query.filter_by(id=id_).first()
        if student:
            mc.set(cache_key, pickle.dumps(student).hex())
            mc.expire(cache_key, ONE_HOUR)
        return student

    @classmethod
    def existed(cls, mobile):
        return bool(cls.query.filter_by(mobile=mobile).first())

    @property
    def wechat_user(self):
        return WechatUser.get_by_open_id(self.open_id)

    @property
    def avatar_url(self):
        return self.wechat_user.avatar_url

    @property
    def avatar(self):
        cache_key = self._avatar_cache_key % self.id
        if mc.get(cache_key):
            return pickle.loads(bytes.fromhex(mc.get(cache_key)))
        return self.cache_avatar(self.id, self.avatar_url)

    @staticmethod
    @app.task
    def cache_avatar(student_id, avatar_url):
        cache_key = Student._avatar_cache_key % student_id
        response = requests.get(avatar_url)
        image = response.content
        mc.set(cache_key, pickle.dumps(image).hex())
        mc.expire(cache_key, ONE_WEEK)
        return image

    @property
    def username(self):
        return self.name if self.name else self.wechat_user.nickname

    @property
    def posts(self, limit=10, offset=0):
        from black_market.model.post.course import CoursePost
        return CoursePost.gets_by_student(self.id, limit, offset)

    def update(self, type_, grade, name=None):
        if name:
            self.name = name.strip()
        self.type = type_.value
        self.grade = grade
        self.update_time = datetime.now()
        db.session.add(self)
        db.session.commit()
        self.clear_cache()
        return Student.get(self.id)

    def change_mobile(self, mobile):
        validator.validate_phone(mobile)
        if Student.existed(mobile):
            raise MobileAlreadyExistedError
        self.mobile = mobile
        db.session.add(self)
        db.session.commit()
        self.clear_cache()

    @property
    def account_status(self):
        return AccountStatus(self.status)

    @property
    def remaining_viewcount(self):
        cache_key = self._remaining_viewcount_by_student_cache_key % self.id
        if rd.get(cache_key):
            viewcount = int(rd.get(cache_key))
            return viewcount
        else:
            rd.set(cache_key, self.MAX_VIEWCOUNT)
            rd.expire(cache_key, ONE_DAY)
            return self.MAX_VIEWCOUNT

    def decr_viewcount(self):
        cache_key = self._remaining_viewcount_by_student_cache_key % self.id
        if rd.get(cache_key):
            viewcount = int(rd.get(cache_key))
            if viewcount <= 0:
                raise CannotViewPostContactError()
            rd.decr(cache_key)

    def need_verify(self):
        return self.account_status is AccountStatus.need_verify

    def is_normal(self):
        return self.account_status is AccountStatus.normal

    def to_normal(self):
        self.status = AccountStatus.normal.value
        db.session.add(self)
        db.session.commit()
        self.clear_cache()

    def delete(self):
        self.clear_cache()
        db.session.delete(self)
        db.session.commit()

    def clear_cache(self):
        mc.delete(self._student_cache_key % self.id)
        mc.delete(self._avatar_cache_key % self.id)
        rd.delete(self._remaining_viewcount_by_student_cache_key % self.id)
