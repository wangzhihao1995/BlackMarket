from flask import Blueprint, request, render_template

from black_market.libs.api import course as course_api


bp = Blueprint('market', __name__)


@bp.route('/', methods=['GET'])
def index():
    return render_template('index.html')


@bp.route('/posts', methods=['GET'])
def posts():
    return render_template('posts.html')


@bp.route('/newpost', methods=['GET'])
def newpost():
    return render_template('newpost.html')


def get_all_courses():
    courses = course_api.get_all_courses()
    s = ''
    for course in courses:
        s = s + str(course.id) + '.\t' + course.name + '<br>'
    return s


@bp.route('/course/<int:id>', methods=['GET'])
def get_course(id=None):
    course = course_api.get_course_by_id(id)
    if not course:
        return 'NULL'
    return str(id) + '. ' + course.name


@bp.route('/course/search', methods=['GET'])
def search_course():
    name = request.values.get('name')
    credit = request.values.get('credit')
    days = request.values.get('days')
    courses = course_api.search_course_by_filters(name, days, credit)
    s = ''
    if courses:
        for course in courses:
            s = s + str(course.id) + '.\t' + course.name + '<br>'
    return s
