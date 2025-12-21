package com.example.crbs_location_room_service.model;

import java.time.Instant;

public class Room {
  private String locationId;
  private String roomId;
  private String roomName;
  private String capacity;
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

  public String getCapacity() {
    return capacity;
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

  public void setCapacity(String capacity) {
    this.capacity = capacity;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }
}
