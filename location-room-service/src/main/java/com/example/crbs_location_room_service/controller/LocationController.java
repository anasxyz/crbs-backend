package com.example.crbs_location_room_service.controller;

import com.example.crbs_location_room_service.model.Location;
import com.example.crbs_location_room_service.service.LocationService;
import org.springframework.web.bind.annotation.*;

@RestController
public class LocationController {

  private final LocationService locationService;

  public LocationController(LocationService locationService) {
    this.locationService = locationService;
  }

  // endpoint to create a location
  @PostMapping("/location")
  public Location createLocation(@RequestBody Location location) {
    return locationService.addLocation(location);
  }

  @GetMapping("/location/{locationId}")
  public Location getLocationById(@PathVariable String locationId) {
    return locationService.getLocationById(locationId);
  }

  @GetMapping("/status")
  public String status() {
    return "Ok\n";
  }
}
