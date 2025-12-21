def test_create_user_success(client, setup_dynamo):
  payload = {"username": "alice", "email": "alice@example.com"}
  response = client.post("/user", json=payload)

  assert response.status_code == 201
  assert "userId" in response.get_json()
