import json

from app import lambda_handler


# test that the lambda handler is deterministic meaning it will always return the same output for the same input
def test_forecast_is_deterministic():
  event = {"body": json.dumps({"locationId": "Dundee", "date": "2025-12-21"})}

  # run twice with the same input
  res1 = lambda_handler(event, None)
  res2 = lambda_handler(event, None)

  assert res1["body"] == res2["body"]
