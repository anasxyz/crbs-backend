from flask import Flask, jsonify, request
from flask_jwt_extended import (
  create_access_token,
  jwt_required,
  get_jwt_identity,
  JWTManager,
)

app = Flask(__name__)

# Configure jwt
app.config["JWT_SECRET_KEY"] = "secret"
jwt = JWTManager(app)


# Status check
@app.route("/status", methods=["GET"])
def status():
  return jsonify({"status": "ok", "service": "authentication service"}), 200


# Hello test
@app.route("/hello", methods=["GET"])
def hello():
  return jsonify({"message": "hello", "service": "authentication service"}), 200


# Login route to get token
@app.route("/login", methods=["POST"])
def login():
  username = request.json.get("username", None)
  password = request.json.get("password", None)

  if username != "test" or password != "password":
    return jsonify({"msg": "Bad username or password"}), 401

  access_token = create_access_token(identity=username)
  return jsonify(access_token=access_token)


# Protected route
@app.route("/protected", methods=["GET"])
@jwt_required()
def protected():
  current_user = get_jwt_identity()
  return jsonify(logged_in_as=current_user), 200


if __name__ == "__main__":
  app.run(debug=True, host="0.0.0.0", port=5000)
