package com.example.crbs_location_room_service.repository;

import com.example.crbs_location_room_service.model.Location;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public class LocationRepository {
  private final DynamoDbClient dynamoDbClient;
  private final String tableName = "Locations";

  public LocationRepository(DynamoDbClient dynamoDbClient) {
    this.dynamoDbClient = dynamoDbClient;
  }

  public Location addLocation(Location location) {
    Map<String, AttributeValue> item = Map.of(
        "locationId", AttributeValue.builder().s(location.getLocationId()).build(),
        "name", AttributeValue.builder().s(location.getName()).build(),
        "city", AttributeValue.builder().s(location.getCity()).build(),
        "createdAt", AttributeValue.builder().s(location.getCreatedAt()).build(),
        "updatedAt", AttributeValue.builder().s(location.getUpdatedAt()).build()
    );

    PutItemRequest putItemRequest = PutItemRequest.builder()
        .tableName(tableName)
        .item(item)
        .build();

    dynamoDbClient.putItem(putItemRequest);
    return location;
  }
}
