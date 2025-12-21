import pytest
from moto import mock_aws
import boto3
from app import app, TABLE_NAME


@pytest.fixture
def client():
  app.config["TESTING"] = True
  with app.test_client() as client:
    yield client


@pytest.fixture
def dynamodb_mock():
  with mock_aws():
    db = boto3.client("dynamodb", region_name="us-east-1")
    # Create the table required by your app
    db.create_table(
      TableName=TABLE_NAME,
      KeySchema=[{"AttributeName": "userId", "KeyType": "HASH"}],
      AttributeDefinitions=[{"AttributeName": "userId", "AttributeType": "S"}],
      ProvisionedThroughput={"ReadCapacityUnits": 1, "WriteCapacityUnits": 1},
    )
    yield db


def test_create_user(client, dynamodb_mock):
  payload = {"username": "johndoe", "email": "john@example.com"}
  response = client.post("/user", json=payload)

  assert response.status_code == 201
  assert "userId" in response.json


def test_get_user(client, dynamodb_mock):
  # First, manually put an item in the mock DB
  user_id = "12345"
  dynamodb_mock.put_item(
    TableName=TABLE_NAME,
    Item={
      "userId": {"S": user_id},
      "username": {"S": "testuser"},
      "email": {"S": "test@test.com"},
      "role": {"S": "USER"},
    },
  )

  response = client.get(f"/user/{user_id}")
  assert response.status_code == 200
  assert response.json["username"] == "testuser"


def test_get_user_not_found(client, dynamodb_mock):
  response = client.get("/user/nonexistent")
  assert response.status_code == 404
