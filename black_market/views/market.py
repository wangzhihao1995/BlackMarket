from flask import Blueprint, render_template, request

from black_market.libs.cache.redis import mc, rd2
from black_market.api.utils import normal_jsonify
from black_market.config import RAW_SALT

bp = Blueprint('market', __name__)

index_page_view_count_cache_key = 'black:market:index:page:view'


@bp.route('/', methods=['GET'])
def index():
    rd2.incr(index_page_view_count_cache_key)
    page_view = int(rd2.get(index_page_view_count_cache_key))
    return render_template('index.html', page_view=page_view)


@bp.route('/clear', methods=['GET'])
def clear_all():
    data = request.args
    pwd = data.get('pwd')
    if pwd != RAW_SALT:
        return normal_jsonify({'status': 'failed'})
    from manage import init_database
    init_database()
    mc.flushdb()
    return normal_jsonify({'status': 'ok'})


@bp.route('/clear/user/<int:id_>', methods=['GET'])
def clear_user(id_):
    data = request.args
    pwd = data.get('pwd')
    if pwd != RAW_SALT:
        return normal_jsonify({'status': 'failed'})
    from black_market.model.user.student import Student
    from black_market.model.wechat.session import WechatSession
    from black_market.model.post.course import CoursePost
    student = Student.get(id_)
    name = student.username
    wechat_user = student.wechat_user
    wechat_session = WechatSession.get_by_open_id(wechat_user.open_id)

    posts = CoursePost.gets_by_student(student.id, limit=100, offset=0)
    for post in posts:
        post.delete()

    student.delete()
    wechat_user.delete()
    wechat_session.delete()
    return normal_jsonify({'status': 'Student %s has been removed.' % name})


@bp.route('/<int:student_id>/<int:supply>/<int:demand>', methods=['GET'])
def existed(student_id, supply, demand):
    from black_market.model.post.course import CoursePost
    r, rs = CoursePost._existed(student_id, supply, demand)
    return normal_jsonify(dict(result=r, rs=rs))
