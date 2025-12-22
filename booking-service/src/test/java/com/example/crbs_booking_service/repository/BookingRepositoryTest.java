package com.example.crbs_booking_service.repository;

import com.example.crbs_booking_service.model.Booking;
import com.example.crbs_booking_service.BaseIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext
class BookingRepositoryTest extends BaseIntegrationTest {

  @Autowired
  private BookingRepository bookingRepository;

  @Autowired
  private DynamoDbClient dynamoDbClient;

  private final String tableName = "Bookings";

  @BeforeEach
  void setUp() {
    try {
      dynamoDbClient.createTable(CreateTableRequest.builder()
          .tableName(tableName)
          .keySchema(KeySchemaElement.builder()
              .attributeName("bookingId")
              .keyType(KeyType.HASH)
              .build())
          .attributeDefinitions(AttributeDefinition.builder()
              .attributeName("bookingId")
              .attributeType(ScalarAttributeType.S)
              .build())
          .provisionedThroughput(ProvisionedThroughput.builder()
              .readCapacityUnits(5L)
              .writeCapacityUnits(5L)
              .build())
          .build());
    } catch (ResourceInUseException ignored) {
      // Table already exists
    }
  }

  @Test
  void testAddAndGetBooking() {
    Booking booking = new Booking();
    booking.setUserId("user-123");
    booking.setRoomId("room-456");
    booking.setBookingDate("2025-12-25");
    booking.setBasePrice(150.0);
    booking.setOperationalCost(25.5);
    booking.setFinalPrice(175.5);
    booking.setStatus("CONFIRMED");

    Booking saved = bookingRepository.addBooking(booking);
    Booking retrieved = bookingRepository.getBookingById(saved.getBookingId());

    assertNotNull(retrieved);
    assertEquals(saved.getBookingId(), retrieved.getBookingId());
    assertEquals("user-123", retrieved.getUserId());
    assertEquals("room-456", retrieved.getRoomId());
    assertEquals("2025-12-25", retrieved.getBookingDate());
    assertEquals(150.0, retrieved.getBasePrice());
    assertEquals(25.5, retrieved.getOperationalCost());
    assertEquals(175.5, retrieved.getFinalPrice());
    assertEquals("CONFIRMED", retrieved.getStatus());
    assertNotNull(retrieved.getCreatedAt());
    assertNotNull(retrieved.getUpdatedAt());
  }

  @Test
  void testGetAllBookings() {
    Booking b1 = new Booking();
    b1.setUserId("user-A");
    b1.setRoomId("room-1");
    b1.setBookingDate("2025-12-22");
    b1.setBasePrice(100.0);
    b1.setOperationalCost(10.0);
    b1.setFinalPrice(110.0);
    b1.setStatus("PENDING");

    Booking b2 = new Booking();
    b2.setUserId("user-B");
    b2.setRoomId("room-2");
    b2.setBookingDate("2025-12-23");
    b2.setBasePrice(200.0);
    b2.setOperationalCost(20.0);
    b2.setFinalPrice(220.0);
    b2.setStatus("COMPLETED");

    bookingRepository.addBooking(b1);
    bookingRepository.addBooking(b2);
    List<Booking> allBookings = bookingRepository.getAllBookings();

    assertTrue(allBookings.size() >= 2);
    assertTrue(allBookings.stream().anyMatch(b -> b.getUserId().equals("user-A")));
    assertTrue(allBookings.stream().anyMatch(b -> b.getUserId().equals("user-B")));
  }
}
