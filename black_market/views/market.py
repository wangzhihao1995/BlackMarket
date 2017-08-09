from flask import Blueprint, render_template

from black_market.libs.cache.redis import mc

bp = Blueprint('market', __name__)

index_page_view_count_cache_key = 'black:market:index:view:count'


@bp.route('/', methods=['GET'])
def index():
    mc.incr(index_page_view_count_cache_key)
    page_view = int(mc.get(index_page_view_count_cache_key))
    return render_template('index.html', page_view=page_view)


@bp.route('/clear', methods=['GET'])
def clear():
    from black_market.api.utils import normal_jsonify
    from manage import init_database
    init_database()
    return normal_jsonify({'status': 'ok'})
