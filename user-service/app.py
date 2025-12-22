from flask import Flask, request, jsonify
import boto3
import uuid

app = Flask(__name__)
dynamodb = boto3.client("dynamodb", region_name="us-east-1")
TABLE_NAME = "Users"


# POST: create user
# TODO: remove, will probably go unused
@app.route("/user", methods=["POST"])
def create_user():
  data = request.json
  user_id = str(uuid.uuid4())

  item = {
    "userId": {"S": user_id},
    "username": {"S": data["username"]},
    "email": {"S": data["email"]},
    "role": {"S": data.get("role", "USER")},
  }

  dynamodb.put_item(TableName=TABLE_NAME, Item=item)
  return jsonify({"userId": user_id, "status": "created"}), 201


# GET: get user by userId
@app.route("/user/<user_id>", methods=["GET"])
def get_user(user_id):
  response = dynamodb.get_item(TableName=TABLE_NAME, Key={"userId": {"S": user_id}})

  item = response.get("Item")
  if not item:
    return jsonify({"error": "User not found"}), 404

  user = {
    "userId": item["userId"]["S"],
    "username": item["username"]["S"],
    "email": item["email"]["S"],
    "role": item["role"]["S"],
  }
  return jsonify(user), 200


if __name__ == "__main__":
  # run host at 0.0.0.0 to make it accessible from outside
  app.run(host="0.0.0.0", port=8081, debug=True)
