import pytest
from moto import mock_dynamodb
import boto3


@mock_dynamodb
def test_get_user():
  import app as my_app  

  dynamodb = boto3.client("dynamodb", region_name="us-east-1")
  dynamodb.create_table(
    TableName=my_app.TABLE_NAME,
    KeySchema=[{"AttributeName": "userId", "KeyType": "HASH"}],
    AttributeDefinitions=[{"AttributeName": "userId", "AttributeType": "S"}],
    BillingMode="PAY_PER_REQUEST",
  )

  test_user_id = "1234"
  dynamodb.put_item(
    TableName=my_app.TABLE_NAME,
    Item={
      "userId": {"S": test_user_id},
      "username": {"S": "bob"},
      "email": {"S": "bob@example.com"},
      "role": {"S": "USER"},
    },
  )

  client = my_app.app.test_client()
  response = client.get(f"/user/{test_user_id}")
  data = response.get_json()

  assert response.status_code == 200
  assert data["userId"] == test_user_id
  assert data["username"] == "bob"
