package com.example.crbs_location_room_service.repository;

import com.example.crbs_location_room_service.model.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import com.example.crbs_location_room_service.BaseIntegrationTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LocationRepositoryTest extends BaseIntegrationTest {

  @Autowired
  private LocationRepository locationRepository;

  @Autowired
  private DynamoDbClient dynamoDbClient;

  @BeforeEach
  void setUp() {
    try {
      dynamoDbClient.createTable(CreateTableRequest.builder()
          .tableName("Locations")
          .keySchema(KeySchemaElement.builder().attributeName("locationId").keyType(KeyType.HASH).build())
          .attributeDefinitions(
              AttributeDefinition.builder().attributeName("locationId").attributeType(ScalarAttributeType.S).build())
          .provisionedThroughput(ProvisionedThroughput.builder().readCapacityUnits(5L).writeCapacityUnits(5L).build())
          .build());
    } catch (ResourceInUseException ignored) {
    }
  }

  @Test
  void shouldSaveAndRetrieveLocation() {
    Location location = new Location();
    location.setLocationId("LOC-IT-1");
    location.setName("Integration Test Wing");
    location.setCity("Test City");
    location.setCreatedAt("2025-12-21");
    location.setUpdatedAt("2025-12-21");

    locationRepository.addLocation(location);

    Location retrieved = locationRepository.getLocationById("LOC-IT-1");
    assertNotNull(retrieved);
    assertEquals("Integration Test Wing", retrieved.getName());
  }
}
