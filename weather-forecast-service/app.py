import json
import hashlib
import random
import base64


def response(status_code, payload, event):
  return {
    "statusCode": status_code,
    "headers": {"Content-Type": "application/json"},
    "body": json.dumps({"result": payload, "fullEvent": event}),
  }


def lambda_handler(event, context):
  print("FULL EVENT")
  print(json.dumps(event, indent=2))

  body_str = event.get("body", "")

  if event.get("isBase64Encoded"):
    body_str = base64.b64decode(body_str).decode("utf-8")

  try:
    body = json.loads(body_str) if body_str else {}
  except json.JSONDecodeError:
    return response(400, {"error": "Invalid JSON"}, event)

  location = body.get("locationId")
  date = body.get("date")

  if not location or not date:
    return response(
      400, {"error": "Missing locationId or date", "receivedBody": body}, event
    )

  seed_str = f"{location}-{date}"
  seed = int(hashlib.sha256(seed_str.encode()).hexdigest(), 16) % (10**8)
  rng = random.Random(seed)
  temperature = rng.randint(-5, 35)

  return response(
    200,
    {"locationId": location, "date": date, "forecastedTemperatureC": temperature},
    event,
  )
