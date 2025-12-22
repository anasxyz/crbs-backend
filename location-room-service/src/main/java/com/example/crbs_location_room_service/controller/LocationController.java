package com.example.crbs_location_room_service.controller;

import com.example.crbs_location_room_service.model.Location;
import com.example.crbs_location_room_service.service.LocationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LocationController {

  private final LocationService locationService;

  public LocationController(LocationService locationService) {
    this.locationService = locationService;
  }

  // create a location
  @PostMapping("/location")
  public Location createLocation(@RequestBody Location location) {
    return locationService.addLocation(location);
  }

  // get location by id
  @GetMapping("/location/{locationId}")
  public Location getLocationById(@PathVariable String locationId) {
    return locationService.getLocationById(locationId);
  }

  // get location by name
  @GetMapping("/location/name/{name}")
  public Location getLocationByName(@PathVariable String name) {
    return locationService.getLocationByName(name);
  }

  @GetMapping("/health")
  public ResponseEntity<String> health() {
    return ResponseEntity.ok("healthy");
  }

  @GetMapping("/")
  public ResponseEntity<String> health2() {
    return ResponseEntity.ok("healthy");
  }
}
