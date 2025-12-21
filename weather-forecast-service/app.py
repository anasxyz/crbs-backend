import json
import hashlib
import random
import base64


def lambda_handler(event, context):
  print(f"FULL EVENT: {json.dumps(event)}")

  body_str = event.get("body", "")

  if event.get("isBase64Encoded"):
    body_str = base64.b64decode(body_str).decode("utf-8")

  try:
    body = json.loads(body_str) if body_str else {}
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

  return {
    "statusCode": 200,
    "headers": {"Content-Type": "application/json"},
    "body": json.dumps(
      {
        "locationId": location,
        "date": date,
        "forecastedTemperatureC": temperature,
      }
    ),
  }
