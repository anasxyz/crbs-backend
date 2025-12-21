package com.example.crbs_location_room_service.service;

import com.example.crbs_location_room_service.model.Room;
import com.example.crbs_location_room_service.repository.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
  private final RoomRepository roomRepository;

  public RoomService(RoomRepository roomRepository) {
    this.roomRepository = roomRepository;
  }

  public Room addRoom(Room room) {
    return roomRepository.addRoom(room);
  }
}
