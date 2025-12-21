import json
from app import lambda_handler

# simulate the event aws lambda receives from an api Gateway
def test_weather_success():
  event = {
    "body": json.dumps({"locationId": "Dundee", "date": "2025-12-21"}),
    "isBase64Encoded": False,
  }

  response = lambda_handler(event, None)
  body = json.loads(response["body"])

  assert response["statusCode"] == 200
  assert body["locationId"] == "Dundee"
  assert "forecastedTemperatureC" in body
