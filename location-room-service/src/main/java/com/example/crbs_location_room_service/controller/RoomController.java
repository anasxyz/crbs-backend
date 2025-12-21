package com.example.crbs_location_room_service.controller;

import com.example.crbs_location_room_service.model.Room;
import com.example.crbs_location_room_service.service.RoomService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
public class RoomController {

  private final RoomService roomService;

  public RoomController(RoomService roomService) {
    this.roomService = roomService;
  }

  // endpoint to create a room
  @PostMapping
  public Room createRoom(@RequestBody Room room) {
    return roomService.addRoom(room);
  }

  @GetMapping
  public String status() {
    return "Ok\n";
  }
}
