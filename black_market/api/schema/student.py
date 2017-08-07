from black_market.ext import ma

from .base import BaseSchema, FillHelperMixin


class CreateStudentSchema(BaseSchema, FillHelperMixin):
    mobile = ma.String(required=True)
    type = ma.Integer(required=True)
    grade = ma.String(required=True)
    verify_code = ma.String(required=True)
    session_key = ma.String(required=True)