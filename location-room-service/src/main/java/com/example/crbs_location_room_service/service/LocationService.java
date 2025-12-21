package com.example.crbs_location_room_service.service;

import com.example.crbs_location_room_service.model.Location;
import com.example.crbs_location_room_service.repository.LocationRepository;
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
}
