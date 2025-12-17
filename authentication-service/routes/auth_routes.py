from flask import Blueprint, jsonify, request
from flask_jwt_extended import create_access_token, jwt_required, get_jwt_identity

# create a blueprint with prefix /auth
auth_bp = Blueprint("auth", __name__, url_prefix="/auth")


@auth_bp.route("/login", methods=["POST"])
def login():
  username = request.json.get("username", None)
  password = request.json.get("password", None)

  if username != "test" or password != "password":
    return jsonify({"msg": "Bad username or password"}), 401

  access_token = create_access_token(identity=username)
  return jsonify(access_token=access_token)


# @auth_bp.route("/signup", methods=["POST"])
def signup():
  pass


@auth_bp.route("/protected", methods=["GET"])
@jwt_required()
def protected():
  current_user = get_jwt_identity()
  return jsonify(logged_in_as=current_user), 200
