package com.example.crbs_location_room_service.service;

import com.example.crbs_location_room_service.model.Location;
import com.example.crbs_location_room_service.repository.LocationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {

  @Mock
  private LocationRepository locationRepository;

  @InjectMocks
  private LocationService locationService;

  @Test
  void testAddLocation() {
    Location location = new Location();
    location.setName("Test Center");

    when(locationRepository.addLocation(location)).thenReturn(location);

    Location result = locationService.addLocation(location);

    assertEquals("Test Center", result.getName());
    verify(locationRepository, times(1)).addLocation(location);
  }

  @Test
  void testGetLocationById() {
    Location location = new Location();
    location.setLocationId("LOC-101");

    when(locationRepository.getLocationById("LOC-101")).thenReturn(location);

    Location result = locationService.getLocationById("LOC-101");

    assertEquals("LOC-101", result.getLocationId());
  }

  @Test
  void testGetLocationByName() {
    Location location = new Location();
    location.setName("Main Building");

    when(locationRepository.getLocationByName("Main Building")).thenReturn(location);

    Location result = locationService.getLocationByName("Main Building");

    assertEquals("Main Building", result.getName());
  }
}
