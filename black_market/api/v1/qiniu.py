from datetime import datetime
from flask import g
from qiniu import put_file, etag

from .._bp import create_blueprint
from black_market.api.utils import normal_jsonify
from black_market.api.decorator import require_session_key
from black_market.libs.qiniu.qiniu import qiniu_client
from black_market.model.user.student import Student
from black_market.model.exceptions import UserNotFoundError

bp = create_blueprint('qiniu', 'v1', __name__, url_prefix='/qiniu')


@bp.route('/token', methods=['POST'])
@require_session_key()
def get_token():
    student = Student.get(g.wechat_user.id)
    if not student:
        raise UserNotFoundError()
    now = datetime.now().strftime('%y%m%d%H%M%S')
    file_name = 'bm-post-pic-s%s-t%s' % (student.id, now)
    token = qiniu_client.get_token(file_name=file_name)
    return normal_jsonify(dict(token=token, file_name=file_name))


@bp.route('/upload', methods=['GET'])
@require_session_key()
def upload():
    # data = request.args.to_dict()
    # token = data.get('token')
    # file_name = data.get('file_name')
    # localfile = 'black_market/static/img/header.jpg'
    # ret, info = put_file(token, file_name, localfile)
    # print(info)
    # return normal_jsonify({})

    now = datetime.now().strftime('%y%m%d%H%M%S')
    ext = 'jpg'

    file_name = 'bm-post-pic-s%s-t%s.%s' % (1, now, ext)
    token = qiniu_client.get_token(file_name=file_name)
    localfile = 'black_market/static/img/header.jpg'
    ret, info = put_file(token, file_name, localfile)
    print(info)
    print(ret)
    assert ret['key'] == file_name
    assert ret['hash'] == etag(localfile)
    return normal_jsonify({})


@bp.route('/callback', methods=['POST'])
@require_session_key()
def callback():
    return normal_jsonify()