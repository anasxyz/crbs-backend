package com.example.crbs_location_room_service.model;

import java.time.Instant;

public class Location {
  private String locationId;
  private String name;
  private String city;
  private String createdAt;
  private String updatedAt;

  // getters
  public String getLocationId() {
    return locationId;
  }

  public String getName() {
    return name;
  }

  public String getCity() {
    return city;
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

  public void setName(String name) {
    this.name = name;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }
}
