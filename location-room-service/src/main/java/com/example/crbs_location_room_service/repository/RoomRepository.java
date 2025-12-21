package com.example.crbs_location_room_service.repository;

import com.example.crbs_location_room_service.model.Room;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryResponse;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class RoomRepository {
  private final DynamoDbClient dynamoDbClient;
  private final String tableName = "Rooms";

  public RoomRepository(DynamoDbClient dynamoDbClient) {
    this.dynamoDbClient = dynamoDbClient;
  }

  // add room
  public Room addRoom(Room room) {
    Map<String, AttributeValue> item = Map.of(
        "locationId", AttributeValue.builder().s(room.getLocationId()).build(),
        "roomId", AttributeValue.builder().s(room.getRoomId()).build(),
        "roomName", AttributeValue.builder().s(room.getRoomName()).build(),
        "capacity", AttributeValue.builder().n(room.getCapacity().toString()).build(),
        "basePrice", AttributeValue.builder().n(room.getBasePrice().toString()).build(),
        "createdAt", AttributeValue.builder().s(room.getCreatedAt()).build(),
        "updatedAt", AttributeValue.builder().s(room.getUpdatedAt()).build());

    PutItemRequest putItemRequest = PutItemRequest.builder()
        .tableName(tableName)
        .item(item)
        .build();

    dynamoDbClient.putItem(putItemRequest);
    return room;
  }

  public Room getRoomById(String roomId) {
    // TODO: change scan because its inefficient for one item lookup
    ScanRequest scanRequest = ScanRequest.builder()
        .tableName(tableName)
        .filterExpression("roomId = :rid")
        .expressionAttributeValues(Map.of(
            ":rid", AttributeValue.builder().s(roomId).build()))
        .build();

    ScanResponse response = dynamoDbClient.scan(scanRequest);

    return response.items().stream()
        .map(this::mapToRoom)
        .findFirst()
        .orElse(null);
  }

  // get all rooms by locationid
  public List<Room> getRoomsByLocationId(String locationId) {
    QueryRequest queryRequest = QueryRequest.builder()
        .tableName(tableName)
        .keyConditionExpression("locationId = :v_id")
        .expressionAttributeValues(Map.of(
            ":v_id", AttributeValue.builder().s(locationId).build()))
        .build();

    QueryResponse response = dynamoDbClient.query(queryRequest);

    return response.items().stream()
        .map(this::mapToRoom)
        .collect(Collectors.toList());
  }

  // get every room in the tbale
  public List<Room> getAllRooms() {
    ScanRequest scanRequest = ScanRequest.builder()
        .tableName(tableName)
        .build();

    ScanResponse response = dynamoDbClient.scan(scanRequest);

    return response.items().stream()
        .map(this::mapToRoom)
        .collect(Collectors.toList());
  }

  private Room mapToRoom(Map<String, AttributeValue> item) {
    if (item == null || item.isEmpty())
      return null;

    Room room = new Room();

    room.setLocationId(item.containsKey("locationId") ? item.get("locationId").s() : "");
    room.setRoomId(item.containsKey("roomId") ? item.get("roomId").s() : "");
    room.setRoomName(item.containsKey("roomName") ? item.get("roomName").s() : "Unknown");

    String capacityVal = (item.containsKey("capacity") && item.get("capacity").n() != null)
        ? item.get("capacity").n()
        : "0";
    room.setCapacity(Integer.parseInt(capacityVal));

    String priceVal = (item.containsKey("basePrice") && item.get("basePrice").n() != null)
        ? item.get("basePrice").n()
        : "0.0";
    room.setBasePrice(Float.parseFloat(priceVal));

    room.setCreatedAt(item.containsKey("createdAt") ? item.get("createdAt").s() : "");
    room.setUpdatedAt(item.containsKey("updatedAt") ? item.get("updatedAt").s() : "");

    return room;
  }
}
