package com.example.crbs_booking_service.repository;

import com.example.crbs_booking_service.model.Booking;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class BookingRepository {

  private final DynamoDbClient dynamoDbClient;
  private final String tableName = "Bookings";

  public BookingRepository(DynamoDbClient dynamoDbClient) {
    this.dynamoDbClient = dynamoDbClient;
  }

  public Booking addBooking(Booking booking) {
    // generate uniqeu bookingid and timestamps
    booking.setBookingId(UUID.randomUUID().toString());
    booking.setCreatedAt(java.time.Instant.now());
    booking.setUpdatedAt(java.time.Instant.now());

    // build item for dynamodb
    PutItemRequest request = PutItemRequest.builder()
        .tableName(tableName)
        .item(Map.of(
            "bookingId", AttributeValue.builder().s(booking.getBookingId()).build(),
            "userId", AttributeValue.builder().s(booking.getUserId()).build(),
            "roomId", AttributeValue.builder().s(booking.getRoomId()).build(),
            "bookingDate", AttributeValue.builder().s(booking.getBookingDate()).build(),
            "basePrice", AttributeValue.builder().n(String.valueOf(booking.getBasePrice())).build(),
            "operationalCost", AttributeValue.builder().n(String.valueOf(booking.getOperationalCost())).build(),
            "finalPrice", AttributeValue.builder().n(String.valueOf(booking.getFinalPrice())).build(),
            "status", AttributeValue.builder().s(booking.getStatus()).build(),
            "createdAt", AttributeValue.builder().s(booking.getCreatedAt().toString()).build(),
            "updatedAt", AttributeValue.builder().s(booking.getUpdatedAt().toString()).build()))
        .build();

    dynamoDbClient.putItem(request);
    return booking;
  }

  public Booking getBookingById(String bookingId) {
    Map<String, AttributeValue> key = Map.of(
        "bookingId", AttributeValue.builder().s(bookingId).build());

    GetItemRequest request = GetItemRequest.builder()
        .tableName(tableName)
        .key(key)
        .build();

    Map<String, AttributeValue> item = dynamoDbClient.getItem(request).item();
    return item.isEmpty() ? null : mapToBooking(item);
  }

  public List<Booking> getAllBookings() {
    ScanRequest scanRequest = ScanRequest.builder()
        .tableName(tableName)
        .build();

    return dynamoDbClient.scan(scanRequest).items().stream()
        .map(this::mapToBooking)
        .collect(Collectors.toList());
  }

  private Booking mapToBooking(Map<String, AttributeValue> item) {
    Booking booking = new Booking();
    booking.setBookingId(item.get("bookingId").s());
    booking.setUserId(item.get("userId").s());
    booking.setRoomId(item.get("roomId").s());
    booking.setBookingDate(item.get("bookingDate").s());
    booking.setBasePrice(Double.parseDouble(item.get("basePrice").n()));
    booking.setOperationalCost(Double.parseDouble(item.get("operationalCost").n()));
    booking.setFinalPrice(Double.parseDouble(item.get("finalPrice").n()));
    booking.setStatus(item.get("status").s());
    booking.setCreatedAt(java.time.Instant.parse(item.get("createdAt").s()));
    booking.setUpdatedAt(java.time.Instant.parse(item.get("updatedAt").s()));
    return booking;
  }
}
