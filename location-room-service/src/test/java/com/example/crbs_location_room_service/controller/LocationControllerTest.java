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
    // Arrange
    Location location = new Location();
    location.setName("Main Campus");
    when(locationService.addLocation(location)).thenReturn(location);

    // Act
    Location result = locationController.createLocation(location);

    // Assert
    assertEquals("Main Campus", result.getName());
  }

  @Test
  void testStatus() {
    // Act
    String result = locationController.status();

    // Assert
    assertEquals("Ok\n", result);
  }
}
