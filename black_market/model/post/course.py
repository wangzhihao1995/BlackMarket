import pickle
from datetime import datetime

from black_market.ext import db
from black_market.libs.cache.redis import mc, rd, ONE_HOUR, ONE_DAY
from black_market.model.user.student import Student
from black_market.model.user.view_record import ViewRecord
from black_market.model.post.course_supply import CourseSupply
from black_market.model.post.course_demand import CourseDemand
from black_market.model.post.consts import PostStatus, OrderType, PostType
from black_market.model.utils.crypto import decrypt, encrypt
from black_market.model.exceptions import (
    SupplySameAsDemandError, InvalidPostError,
    DuplicatedPostError, CannotEditPostError)


class CoursePost(db.Model):
    __tablename__ = 'course_post'

    _cache_key_prefix = 'course:post:'
    _course_post_by_id_cache_key = _cache_key_prefix + 'id:%s'
    _post_pv_by_id_cache_key = _cache_key_prefix + 'pv:id:%s'
    _course_post_supply_by_post_id_cache_key = _cache_key_prefix + 'supply:id:%s'
    _course_post_demand_by_post_id_cache_key = _cache_key_prefix + 'demand:id:%s'

    MAX_EDIT_TIMES = 5

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    student_id = db.Column(db.Integer, db.ForeignKey('student.id'))
    status_ = db.Column(db.SmallInteger)
    switch = db.Column(db.SmallInteger)
    mobile = db.Column(db.String(80))
    wechat = db.Column(db.String(80))
    message = db.Column(db.String(256))
    pv_ = db.Column(db.Integer, default=0)
    editable = db.Column(db.SmallInteger, default=MAX_EDIT_TIMES)
    create_time = db.Column(db.DateTime, default=datetime.now)
    update_time = db.Column(db.DateTime, default=datetime.now)

    def __init__(self, student_id, switch, mobile, wechat, message, status=PostStatus.normal):
        self.student_id = student_id
        self.switch = switch.value
        self.mobile = mobile
        self.wechat = wechat
        self.message = message
        self.status_ = status.value

    def dump(self):
        return dict(
            id=self.id, student=self.student.dump(),
            supply=self.supply.share_dump() if self.supply else dict(),
            demand=self.demand.share_dump() if self.demand else dict(),
            switch=self.switch, mobile=self.mobile, wechat=self.wechat,
            message=self.message, pv=self.pv, status=self.status_,
            editable=self.editable, create_time=self.create_time,
            update_time=self.update_time, fuzzy_id=self.fuzzy_id)

    def share_dump(self):
        return dict(
            id=self.id, student=self.student.share_dump(),
            supply=self.supply.share_dump() if self.supply else dict(),
            demand=self.demand.share_dump() if self.demand else dict(),
            message=self.message, pv=self.pv, status=self.status_,
            create_time=self.create_time, update_time=self.update_time)

    @classmethod
    def get(cls, id_):
        cache_key = cls._course_post_by_id_cache_key % id_
        if mc.get(cache_key):
            return pickle.loads(bytes.fromhex(mc.get(cache_key)))
        post = CoursePost.query.get(id_)
        if post:
            mc.set(cache_key, pickle.dumps(post).hex())
            mc.expire(cache_key, ONE_HOUR)
        return post

    @classmethod
    def gets(cls, limit=5, offset=0, order=OrderType.descending,
             closed=1, supply=None, demand=None):
        if supply and demand:
            return cls.gets_by_supply_and_demand(
                limit, offset, order, supply, demand, closed)
        elif supply and not demand:
            return cls.gets_by_supply(limit, offset, order, supply, closed)
        elif not supply and demand:
            return cls.gets_by_demand(limit, offset, order, demand, closed)
        if order is OrderType.ascending:
            if closed:
                return CoursePost.query.limit(limit).offset(offset).all()
            elif not closed:
                return CoursePost.query.filter_by(
                    status_=PostStatus.normal.value).limit(limit).offset(offset).all()
        if closed:
            return CoursePost.query.order_by(db.desc(cls.id)).limit(limit).offset(offset).all()
        elif not closed:
            return CoursePost.query.filter_by(
                status_=PostStatus.normal.value).order_by(
                    db.desc(cls.id)).limit(limit).offset(offset).all()

    @classmethod
    def gets_by_supply_and_demand(cls, limit, offset, order, supply, demand, closed):
        desc = 'desc' if order is OrderType.descending else ''
        sql = ('select course_supply.post_id as post_id '
               'from course_supply join course_demand '
               'on course_supply.post_id=course_demand.post_id '
               'where course_supply.course_id={supply} '
               'and course_demand.course_id={demand} '
               'order by post_id {desc} limit {offset}, {limit}'.format(
                   supply=supply, demand=demand, desc=desc,
                   offset=offset, limit=limit))
        if not closed:
            sql = ('select course_supply.post_id as post_id '
                   'from course_supply join course_demand '
                   'on course_supply.post_id=course_demand.post_id '
                   'join course_post on course_post.id=course_supply.post_id '
                   'where course_supply.course_id={supply} '
                   'and course_demand.course_id={demand} '
                   'and course_post.status_={status} '
                   'order by post_id {desc} limit {offset}, {limit}'.format(
                       supply=supply, demand=demand, status=PostStatus.normal.value,
                       desc=desc, offset=offset, limit=limit))
        rs = db.engine.execute(sql)
        return [CoursePost.get(post_id) for (post_id,) in rs]

    @classmethod
    def gets_by_supply(cls, limit, offset, order, supply, closed):
        desc = 'desc' if order is OrderType.descending else ''
        sql = ('select course_supply.post_id as post_id '
               'from course_supply join course_demand '
               'on course_supply.post_id=course_demand.post_id '
               'where course_supply.course_id={supply} '
               'order by post_id {desc} limit {offset}, {limit}'.format(
                   supply=supply, desc=desc, offset=offset, limit=limit))
        if not closed:
            sql = ('select course_supply.post_id as post_id '
                   'from course_supply join course_demand '
                   'on course_supply.post_id=course_demand.post_id '
                   'join course_post on course_post.id=course_supply.post_id '
                   'where course_supply.course_id={supply} '
                   'and course_post.status_={status} '
                   'order by post_id {desc} limit {offset}, {limit}'.format(
                       supply=supply, status=PostStatus.normal.value,
                       desc=desc, offset=offset, limit=limit))
        rs = db.engine.execute(sql)
        return [CoursePost.get(post_id) for (post_id,) in rs]

    @classmethod
    def gets_by_demand(cls, limit, offset, order, demand, closed):
        desc = 'desc' if order is OrderType.descending else ''
        sql = ('select course_supply.post_id as post_id '
               'from course_supply join course_demand '
               'on course_supply.post_id=course_demand.post_id '
               'where course_demand.course_id={demand} '
               'order by post_id {desc} limit {offset}, {limit}'.format(
                   demand=demand, desc=desc, offset=offset, limit=limit))
        if not closed:
            sql = ('select course_supply.post_id as post_id '
                   'from course_supply join course_demand '
                   'on course_supply.post_id=course_demand.post_id '
                   'join course_post on course_post.id=course_supply.post_id '
                   'where course_demand.course_id={demand} '
                   'and course_post.status_={status} '
                   'order by post_id {desc} limit {offset}, {limit}'.format(
                       demand=demand, status=PostStatus.normal.value,
                       desc=desc, offset=offset, limit=limit))
        rs = db.engine.execute(sql)
        return [CoursePost.get(post_id) for (post_id,) in rs]

    @classmethod
    def gets_by_student(cls, student_id, limit=10, offset=0, order=OrderType.descending):
        if order is OrderType.descending:
            return CoursePost.query.order_by(db.desc(cls.id)).filter_by(
                student_id=student_id).limit(limit).offset(offset).all()
        return CoursePost.query.filter_by(
            student_id=student_id).limit(limit).offset(offset).all()

    @classmethod
    def add(cls, student_id, supply_course_id, demand_course_id, switch, mobile, wechat, message):
        cls.validate_new_post(student_id, supply_course_id, demand_course_id)
        cls.validate_supply_and_demand(supply_course_id, demand_course_id)
        post = CoursePost(student_id, switch, mobile, wechat, message)
        db.session.add(post)
        db.session.commit()
        CourseSupply.add(post.id, supply_course_id)
        CourseDemand.add(post.id, demand_course_id)
        return post

    @classmethod
    def existed(cls, student_id, supply, demand):
        sql = ('select course_supply.post_id as post_id '
               'from course_supply join course_demand '
               'on course_supply.post_id=course_demand.post_id '
               'where course_supply.course_id={supply} '
               'and course_demand.course_id={demand}').format(
                   supply=supply, demand=demand)
        rs = db.engine.execute(sql)
        post_ids = [str(post_id) for (post_id,) in rs]
        if post_ids:
            sql = ('select id from course_post '
                   'where id in {post_ids} '
                   'and status_={status} '
                   'and student_id={student_id}').format(
                post_ids='(%s)' % ','.join(post_ids),
                student_id=student_id,
                status=PostStatus.normal.value)
            rs = db.engine.execute(sql)
            post_ids = [str(id) for (id,) in rs] if rs else []
            return bool(post_ids)
        return False

    @classmethod
    def validate_new_post(cls, student_id, supply_course_id, demand_course_id):
        cls.validate_supply_and_demand(supply_course_id, demand_course_id)
        if cls.existed(student_id, supply_course_id, demand_course_id):
            raise DuplicatedPostError()

    @staticmethod
    def validate_supply_and_demand(supply_course_id, demand_course_id):
        if supply_course_id == demand_course_id:
            raise SupplySameAsDemandError()

        if not supply_course_id and not demand_course_id:
            raise InvalidPostError()

    @classmethod
    def defuzzy(cls, fuzzy_id):
        return decrypt(fuzzy_id)

    @property
    def fuzzy_id(self):
        return encrypt(str(self.id))

    @property
    def student(self):
        return Student.get(self.student_id)

    @property
    def supply(self):
        cache_key = self._course_post_supply_by_post_id_cache_key % self.id
        if mc.get(cache_key):
            return pickle.loads(bytes.fromhex(mc.get(cache_key)))
        course_supply = CourseSupply.get_by_post(self.id)
        if course_supply and course_supply.course_id:
            mc.set(cache_key, pickle.dumps(course_supply).hex())
            mc.expire(cache_key, ONE_DAY)
            return course_supply
        return None

    @property
    def demand(self):
        cache_key = self._course_post_demand_by_post_id_cache_key % self.id
        if mc.get(cache_key):
            return pickle.loads(bytes.fromhex(mc.get(cache_key)))
        course_demand = CourseDemand.get_by_post(self.id)
        if course_demand and course_demand.course_id:
            mc.set(cache_key, pickle.dumps(course_demand).hex())
            mc.expire(cache_key, ONE_DAY)
            return course_demand
        return None

    @property
    def status(self):
        return PostStatus(self.status_)

    def _get_pv(self):
        key = self._post_pv_by_id_cache_key % self.id
        cached = int(rd.get(key)) if rd.get(key) else None
        if cached is not None:
            return cached
        rd.set(key, self.pv_)
        return self.pv_

    def _set_pv(self, pv_):
        rd.set(self._post_pv_by_id_cache_key % self.id, pv_)
        if pv_ % 7 == 0:
            if self.pv_ > pv_:
                pv_ += 7
            self.pv_ = pv_
            db.session.add(self)
            db.session.commit()
            self.clear_cache()

    pv = property(_get_pv, _set_pv)

    def update_self(self, data):
        if not data:
            return True
        if not self.editable:
            raise CannotEditPostError()
        message = data.get('message')
        switch = data.get('switch')
        wechat = data.get('wechat')
        if message:
            self.message = message
        if switch is not None:
            self.switch = switch
        if wechat is not None:
            self.wechat = wechat
        self.update_time = datetime.now()
        self.editable -= 1
        db.session.add(self)
        db.session.commit()
        self.clear_cache()
        return True

    def update_status(self, status):
        if status is PostStatus.normal:
            self.to_normal()
        if status is PostStatus.succeed:
            self.to_succeed()
        if status is PostStatus.abandoned:
            self.to_abandoned()

    def to_normal(self):
        if self.status is not PostStatus.normal:
            self.status_ = PostStatus.normal.value
            self.update_time = datetime.now()
            db.session.add(self)
            db.session.commit()
            self.clear_cache()

    def to_succeed(self):
        if self.status is not PostStatus.succeed:
            self.status_ = PostStatus.succeed.value
            self.update_time = datetime.now()
            db.session.add(self)
            db.session.commit()
            self.clear_cache()
            self.clear_related_view_records()

    def to_abandoned(self):
        if self.status is not PostStatus.abandoned:
            self.status_ = PostStatus.abandoned.value
            self.update_time = datetime.now()
            db.session.add(self)
            db.session.commit()
            self.clear_cache()
            self.clear_related_view_records()

    def update_supply(self, supply_course_id):
        supply = self.supply
        demand = self.demand
        self.validate_supply_and_demand(supply_course_id, demand.course_id)
        supply.course_id = supply_course_id
        self.update_time = datetime.now()
        db.session.add(supply)
        db.session.add(self)
        db.session.commit()
        self.clear_cache()

    def update_demand(self, demand_course_id):
        supply = self.supply
        demand = self.demand
        self.validate_supply_and_demand(supply.course_id, demand_course_id)
        demand.course_id = demand_course_id
        self.update_time = datetime.now()
        db.session.add(demand)
        db.session.add(self)
        db.session.commit()
        self.clear_cache()

    def clear_related_view_records(self):
        ViewRecord.delete_records_by_post(self.id, PostType.course_post)

    def delete(self):
        self.clear_cache()
        supply = CourseSupply.get_by_post(self.id)
        demand = CourseDemand.get_by_post(self.id)
        supply.delete()
        demand.delete()
        db.session.delete(self)
        db.session.commit()

    def clear_cache(self):
        mc.delete(self._course_post_by_id_cache_key % self.id)
        mc.delete(self._course_post_supply_by_post_id_cache_key % self.id)
        mc.delete(self._course_post_demand_by_post_id_cache_key % self.id)
