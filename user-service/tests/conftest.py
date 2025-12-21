import pytest
import boto3
from moto import mock_aws
from app import app


@pytest.fixture
def client():
  app.config["TESTING"] = True
  with app.test_client() as client:
    yield client


@pytest.fixture
def setup_dynamo():
  with mock_aws():
    conn = boto3.client("dynamodb", region_name="us-east-1")
    conn.create_table(
      TableName="Users",
      KeySchema=[{"AttributeName": "userId", "KeyType": "HASH"}],
      AttributeDefinitions=[{"AttributeName": "userId", "AttributeType": "S"}],
      ProvisionedThroughput={"ReadCapacityUnits": 5, "WriteCapacityUnits": 5},
    )
    yield conn
