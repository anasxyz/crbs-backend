import json
from app import lambda_handler


def test_invalid_json():
  event = {"body": "mk", "isBase64Encoded": False}
  response = lambda_handler(event, None)
  assert response["statusCode"] == 400
  assert "Invalid JSON" in response["body"]
