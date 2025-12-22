package com.example.crbs_location_room_service.repository;

import com.example.crbs_location_room_service.model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import com.example.crbs_location_room_service.BaseIntegrationTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoomRepositoryTest extends BaseIntegrationTest {

  @Autowired
  private RoomRepository roomRepository;

  @Autowired
  private DynamoDbClient dynamoDbClient;

  private final String tableName = "Rooms";

  @BeforeEach
  void setUp() {
    try {
      dynamoDbClient.createTable(CreateTableRequest.builder()
          .tableName(tableName)
          .keySchema(
              KeySchemaElement.builder().attributeName("locationId").keyType(KeyType.HASH).build(),
              KeySchemaElement.builder().attributeName("roomId").keyType(KeyType.RANGE).build())
          .attributeDefinitions(
              AttributeDefinition.builder().attributeName("locationId").attributeType(ScalarAttributeType.S).build(),
              AttributeDefinition.builder().attributeName("roomId").attributeType(ScalarAttributeType.S).build())
          .provisionedThroughput(ProvisionedThroughput.builder().readCapacityUnits(5L).writeCapacityUnits(5L).build())
          .build());
    } catch (ResourceInUseException ignored) {
      // table already exists
    }
  }

  @Test
  void testAddAndGetRoom() {
    Room room = new Room();
    room.setLocationId("LOC-1");
    room.setRoomId("RM-101");
    room.setRoomName("Conference Room");
    room.setCapacity(10);
    room.setBasePrice(100.0f);
    room.setCreatedAt("2025-01-01");
    room.setUpdatedAt("2025-01-01");

    roomRepository.addRoom(room);

    Room retrieved = roomRepository.getRoomById("RM-101");
    assertNotNull(retrieved);
    assertEquals("Conference Room", retrieved.getRoomName());
  }

  @Test
  void testGetRoomsByLocationId() {
    Room room = new Room();
    room.setLocationId("LOC-2");
    room.setRoomId("RM-202");
    room.setRoomName("Test Room"); 
    room.setCapacity(5);
    room.setBasePrice(50.0f);
    room.setCreatedAt("2025-12-21"); 
    room.setUpdatedAt("2025-12-21");

    roomRepository.addRoom(room);

    List<Room> rooms = roomRepository.getRoomsByLocationId("LOC-2");
    assertFalse(rooms.isEmpty());
    assertEquals("LOC-2", rooms.get(0).getLocationId());
  }
}
