package com.example.crbs_location_room_service.repository;

import com.example.crbs_location_room_service.model.Room;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

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
        "updatedAt", AttributeValue.builder().s(room.getUpdatedAt()).build()
    );

    PutItemRequest putItemRequest = PutItemRequest.builder()
        .tableName(tableName)
        .item(item)
        .build();

    dynamoDbClient.putItem(putItemRequest);
    return room;
  }
}
