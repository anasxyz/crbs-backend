package com.example.crbs_location_room_service.model;

import java.time.Instant;

public class Room {
  private String locationId;
  private String roomId;
  private String roomName;
  private Integer capacity;
  private Float basePrice;
  private String createdAt;
  private String updatedAt;

  // getters
  public String getLocationId() {
    return locationId;
  }

  public String getRoomId() {
    return roomId;
  }

  public String getRoomName() {
    return roomName;
  }

  public Integer getCapacity() {
    return capacity;
  }

  public Float getBasePrice() {
    return basePrice;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  } 

  // setters
  public void setLocationId(String locationId) {
    this.locationId = locationId;
  }

  public void setRoomId(String roomId) {
    this.roomId = roomId;
  }

  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }

  public void setCapacity(Integer capacity) {
    this.capacity = capacity;
  }

  public void setBasePrice(Float basePrice) {
    this.basePrice = basePrice;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }
}
