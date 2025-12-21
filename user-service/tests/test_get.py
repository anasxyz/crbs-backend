def test_get_user_not_found(client, setup_dynamo):
  response = client.get("/user/non-existent-id")
  assert response.status_code == 404
  assert response.get_json()["error"] == "User not found"


def test_get_user_success(client, setup_dynamo):
  # Create user first
  user_data = {"username": "bob", "email": "bob@example.com"}
  post_res = client.post("/user", json=user_data)
  user_id = post_res.get_json()["userId"]

  # Now retrieve it
  response = client.get(f"/user/{user_id}")
  assert response.status_code == 200
  assert response.get_json()["username"] == "bob"
