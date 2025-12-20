package com.example.crbs.repository;

import com.example.crbs.model.Booking;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.Map;
import java.util.UUID;

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
}
