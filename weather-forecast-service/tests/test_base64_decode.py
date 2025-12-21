import base64
import json

from app import lambda_handler

# test that the lambda handler can decode the base64 encoded body
def test_base64_decoding():
  payload = {"locationId": "Paris", "date": "2025-12-21"}
  encoded_body = base64.b64encode(json.dumps(payload).encode()).decode()

  event = {"body": encoded_body, "isBase64Encoded": True}

  response = lambda_handler(event, None)
  assert response["statusCode"] == 200
