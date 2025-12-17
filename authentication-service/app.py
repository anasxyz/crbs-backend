from flask import Flask, jsonify
from flask_jwt_extended import JWTManager
from flask_cors import CORS
from routes.auth_routes import auth_bp

app = Flask(__name__)


# configure jwt
app.config["JWT_SECRET_KEY"] = "secret"
jwt = JWTManager(app)

# enable cors
CORS(
  app,
  origins=["http://localhost:3000"],  # TODO: replace with env variable
  supports_credentials=True,
  methods=["GET", "POST", "OPTIONS"],
  allow_headers=["Content-Type", "Authorization"],
)

# register the auth blueprint
app.register_blueprint(auth_bp)


# non auth routes
@app.route("/status", methods=["GET"])
def status():
  return jsonify({"status": "ok", "service": "authentication service"}), 200


@app.route("/hello", methods=["GET"])
def hello():
  return jsonify({"message": "hello", "service": "authentication service"}), 200


if __name__ == "__main__":
  app.run(debug=True, host="0.0.0.0", port=5000)
