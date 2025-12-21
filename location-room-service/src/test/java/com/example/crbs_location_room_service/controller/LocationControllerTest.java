package com.example.crbs_location_room_service.controller;

import com.example.crbs_location_room_service.model.Location;
import com.example.crbs_location_room_service.service.LocationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationControllerTest {

  @Mock
  private LocationService locationService;

  @InjectMocks
  private LocationController locationController;

  @Test
  void testCreateLocation() {
    Location location = new Location();
    location.setLocationId("LOC1");
    location.setName("Main Hall");

    when(locationService.addLocation(location)).thenReturn(location);

    Location result = locationController.createLocation(location);
    assertEquals("Main Hall", result.getName());
  }

  @Test
  void testGetLocationById() {
    Location location = new Location();
    location.setLocationId("LOC1");

    when(locationService.getLocationById("LOC1")).thenReturn(location);

    Location result = locationController.getLocationById("LOC1");
    assertEquals("LOC1", result.getLocationId());
  }

  @Test
  void testGetLocationByName() {
    Location location = new Location();
    location.setName("Main Hall");

    when(locationService.getLocationByName("Main Hall")).thenReturn(location);

    Location result = locationController.getLocationByName("Main Hall");
    assertEquals("Main Hall", result.getName());
  }

  @Test
  void testStatus() {
    String result = locationController.status();
    assertEquals("Ok\n", result);
  }
}
