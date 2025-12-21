import pytest
import os
import boto3
from moto import mock_aws
from app import app


@pytest.fixture(autouse=True)
def aws_credentials():
  """mock aws credentials for moto"""
  os.environ["AWS_ACCESS_KEY_ID"] = "testing"
  os.environ["AWS_SECRET_ACCESS_KEY"] = "testing"
  os.environ["AWS_SECURITY_TOKEN"] = "testing"
  os.environ["AWS_SESSION_TOKEN"] = "testing"
  os.environ["AWS_DEFAULT_REGION"] = "us-east-1"


@pytest.fixture
def setup_dynamo(aws_credentials):
  with mock_aws():
    conn = boto3.client("dynamodb", region_name="us-east-1")
    conn.create_table(
      TableName="Users",
      KeySchema=[{"AttributeName": "userId", "KeyType": "HASH"}],
      AttributeDefinitions=[{"AttributeName": "userId", "AttributeType": "S"}],
      ProvisionedThroughput={"ReadCapacityUnits": 5, "WriteCapacityUnits": 5},
    )
    yield conn


@pytest.fixture
def client():
  app.config["TESTING"] = True
  with app.test_client() as client:
    yield client
