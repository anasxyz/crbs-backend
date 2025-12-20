package com.example.crbs.model;

import java.time.Instant;

public class Booking {
  private String bookingId;
  private String userId;
  private String roomId;
  private String bookingDate;
  private double basePrice;
  private double operationalCost;
  private double finalPrice;
  private String status;
  private Instant createdAt;
  private Instant updatedAt;
}
