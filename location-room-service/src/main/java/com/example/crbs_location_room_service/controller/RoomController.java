package com.example.crbs_location_room_service.controller;

import com.example.crbs_location_room_service.model.Room;
import com.example.crbs_location_room_service.service.RoomService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
public class RoomController {

  private final RoomService roomService;

  public RoomController(RoomService roomService) {
    this.roomService = roomService;
  }

  @GetMapping("/room")
  public List<Room> getAllRooms() {
    return roomService.getAllRooms();
  }

  // endpoint to create a room
  @PostMapping("/room")
  public Room createRoom(@RequestBody Room room) {
    return roomService.addRoom(room);
  }

  @GetMapping("/room/{roomId}")
  public Room getRoomById(@PathVariable String roomId) {
    return roomService.getRoomById(roomId);
  }

  @GetMapping("/location/{locationId}/rooms")
  public List<Room> getRoomsByLocationId(@PathVariable String locationId) {
    return roomService.getRoomsByLocationId(locationId);
  }
}
