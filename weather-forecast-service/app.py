import json
import hashlib
import random


def lambda_handler(event, context):
  # log api gateway event
  print(f"FULL EVENT: {json.dumps(event)}") 

  try:
    body = json.loads(event.get("body", "{}"))
  except json.JSONDecodeError:
    return {"statusCode": 400, "body": json.dumps({"error": "Invalid JSON"})}

  location = body.get("locationId")
  date = body.get("date")

  if not location or not date:
    return {
      "statusCode": 400,
      "body": json.dumps({"error": "Missing locationId or date", "received": body}),
    }

  seed_str = f"{location}-{date}"
  seed = int(hashlib.sha256(seed_str.encode()).hexdigest(), 16) % (10**8)
  rng = random.Random(seed)
  temperature = rng.randint(-5, 35)

  response = {
    "locationId": location,
    "date": date,
    "forecastedTemperatureC": temperature,
  }

  return {
    "statusCode": 200,
    "headers": {"Content-Type": "application/json"},
    "body": json.dumps(response),
  }
