package com.example.crbs_booking_service.model;

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

  // getters
  public String getBookingId() {
    return bookingId;
  }

  public String getUserId() {
    return userId;
  }

  public String getRoomId() {
    return roomId;
  }

  public String getBookingDate() {
    return bookingDate;
  }

  public double getBasePrice() {
    return basePrice;
  }

  public double getOperationalCost() {
    return operationalCost;
  }

  public double getFinalPrice() {
    return finalPrice;
  }

  public String getStatus() {
    return status;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  // setters
  public void setBookingId(String bookingId) {
    this.bookingId = bookingId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public void setRoomId(String roomId) {
    this.roomId = roomId;
  }

  public void setBookingDate(String bookingDate) {
    this.bookingDate = bookingDate;
  }

  public void setBasePrice(double basePrice) {
    this.basePrice = basePrice;
  }

  public void setOperationalCost(double operationalCost) {
    this.operationalCost = operationalCost;
  }

  public void setFinalPrice(double finalPrice) {
    this.finalPrice = finalPrice;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }
}
