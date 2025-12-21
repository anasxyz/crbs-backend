# CreateUserOnSignUp
# Lambda function for creating user in Users table on Cognito user sign up event
# Runs as a post confirmation lambda trigger on Cognito

# Disable linting / type checking for this file
# noqa
# type: ignore

import boto3
import json

dynamodb = boto3.resource("dynamodb")
table = dynamodb.Table("Users")


def lambda_handler(event, context):
    print("Received event: " + json.dumps(event))

    user_attrs = event["request"]["userAttributes"]

    user_id = user_attrs.get("sub")
    email = user_attrs.get("email")

    username = user_attrs.get("preferred_username") or email

    try:
        table.put_item(
            Item={
                "userId": user_id,
                "email": email,
                "username": username,
                "role": "USER",
            }
        )
    except Exception as e:
        print(f"DynamoDB Error: {e}")
        raise e

    return event
