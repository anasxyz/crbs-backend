import json
import pytest
from moto import mock_dynamodb
import boto3


@mock_dynamodb
def test_create_user(monkeypatch):
  import app as my_app

  dynamodb = boto3.client("dynamodb", region_name="us-east-1")
  dynamodb.create_table(
    TableName=my_app.TABLE_NAME,
    KeySchema=[{"AttributeName": "userId", "KeyType": "HASH"}],
    AttributeDefinitions=[{"AttributeName": "userId", "AttributeType": "S"}],
    BillingMode="PAY_PER_REQUEST",
  )

  client = my_app.app.test_client()
  payload = {"username": "alice", "email": "alice@example.com"}
  response = client.post("/user", json=payload)

  data = response.get_json()
  assert response.status_code == 201
  assert "userId" in data
  assert data["status"] == "created"
