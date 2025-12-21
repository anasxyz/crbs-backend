import json
import hashlib
import random


def lambda_handler(event, context):
  location = event.get("locationId")
  date = event.get("date")

  if not location or not date:
    return {
      "statusCode": 400,
      "body": json.dumps({"error": "Missing locationId or date"}),
  }

  # always return the same temperature for the same location and date
  seed_str = f"{location}-{date}"
  seed = int(hashlib.sha256(seed_str.encode()).hexdigest(), 16) % (10**8)
  rng = random.Random(seed)

  # between -5 and 35 or whatever
  temperature = rng.randint(-5, 35)

  response = {
    "locationId": location,
    "date": date,
    "forecastedTemperatureC": temperature,
  }

  return {"statusCode": 200, "body": json.dumps(response)}
