package com.example.crbs_location_room_service.repository;

import com.example.crbs_location_room_service.model.Room;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryResponse;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public class RoomRepository {
  private final DynamoDbClient dynamoDbClient;
  private final String tableName = "Rooms";

  public RoomRepository(DynamoDbClient dynamoDbClient) {
    this.dynamoDbClient = dynamoDbClient;
  }

  public Room addRoom(Room room) {
    Map<String, AttributeValue> item = Map.of(
        "locationId", AttributeValue.builder().s(room.getLocationId()).build(),
        "roomId", AttributeValue.builder().s(room.getRoomId()).build(),
        "roomName", AttributeValue.builder().s(room.getRoomName()).build(),
        "capacity", AttributeValue.builder().s(room.getCapacity()).build(),
        "createdAt", AttributeValue.builder().s(room.getCreatedAt()).build(),
        "updatedAt", AttributeValue.builder().s(room.getUpdatedAt()).build());

    PutItemRequest putItemRequest = PutItemRequest.builder()
        .tableName(tableName)
        .item(item)
        .build();

    dynamoDbClient.putItem(putItemRequest);
    return room;
  }

  public List<Room> getRoomsByLocationId(String locationId) {
    QueryRequest queryRequest = QueryRequest.builder()
        .tableName("Rooms")
        .keyConditionExpression("locationId = :v_id")
        .expressionAttributeValues(Map.of(
            ":v_id", AttributeValue.builder().s(locationId).build()))
        .build();

    QueryResponse response = dynamoDbClient.query(queryRequest);

    return response.items().stream().map(item -> {
      Room room = new Room();
      room.setLocationId(item.get("locationId").s());
      room.setRoomId(item.get("roomId").s());
      room.setRoomName(item.get("roomName").s());
      room.setCapacity(item.get("capacity").s());
      room.setCreatedAt(item.get("createdAt").s());
      room.setUpdatedAt(item.get("updatedAt").s());
      return room;
    }).toList();
  }
}
