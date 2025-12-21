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
        "updatedAt", AttributeValue.builder().s(location.getUpdatedAt()).build());

    PutItemRequest putItemRequest = PutItemRequest.builder()
        .tableName(tableName)
        .item(item)
        .build();

    dynamoDbClient.putItem(putItemRequest);
    return location;
  }

  public Location getLocationById(String locationId) {
    Map<String, AttributeValue> key = Map.of(
        "locationId", AttributeValue.builder().s(locationId).build());

    software.amazon.awssdk.services.dynamodb.model.GetItemRequest getItemRequest = software.amazon.awssdk.services.dynamodb.model.GetItemRequest
        .builder()
        .tableName(tableName)
        .key(key)
        .build();

    Map<String, AttributeValue> item = dynamoDbClient.getItem(getItemRequest).item();

    if (item == null || item.isEmpty()) {
      return null;
    }

    Location location = new Location();
    location.setLocationId(item.get("locationId").s());
    location.setName(item.get("name").s());
    location.setCity(item.get("city").s());
    location.setCreatedAt(item.get("createdAt").s());
    location.setUpdatedAt(item.get("updatedAt").s());

    return location;
  }

  public Location getLocationByName(String name) {
    software.amazon.awssdk.services.dynamodb.model.ScanRequest scanRequest = software.amazon.awssdk.services.dynamodb.model.ScanRequest
        .builder()
        .tableName(tableName)
        .filterExpression("#n = :val")
        .expressionAttributeNames(Map.of("#n", "name"))
        .expressionAttributeValues(Map.of(
            ":val", AttributeValue.builder().s(name).build()))
        .build();

    var response = dynamoDbClient.scan(scanRequest);

    if (!response.hasItems() || response.items().isEmpty()) {
      return null;
    }

    Map<String, AttributeValue> item = response.items().get(0);

    Location location = new Location();
    location.setLocationId(item.get("locationId").s());
    location.setName(item.get("name").s());
    location.setCity(item.get("city").s());
    location.setCreatedAt(item.get("createdAt").s());
    location.setUpdatedAt(item.get("updatedAt").s());

    return location;
  }
}
