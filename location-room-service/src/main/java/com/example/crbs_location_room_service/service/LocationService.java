package com.example.crbs_location_room_service.service;

import com.example.crbs_location_room_service.model.Location;
import com.example.crbs_location_room_service.repository.LocationRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class LocationService {
  private final LocationRepository locationRepository;

  public LocationService(LocationRepository locationRepository) {
    this.locationRepository = locationRepository;
  }

  public Location addLocation(Location location) {
    return locationRepository.addLocation(location);
  }

  public Location getLocationById(String locationId) {
    return locationRepository.getLocationById(locationId);
  }

  public Location getLocationByName(String name) {
    return locationRepository.getLocationByName(name);
  }

  public List<Location> getAllLocations() {
    return locationRepository.getAllLocations();
  }
}
