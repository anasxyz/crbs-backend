package com.example.crbs_location_room_service.service;

import com.example.crbs_location_room_service.model.Room;
import com.example.crbs_location_room_service.repository.RoomRepository;

import java.util.List;

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

  public List<Room> getAllRooms() {
    return roomRepository.getAllRooms();
  }

  public Room getRoomById(String roomId) {
    return roomRepository.getRoomById(roomId);
  }

  public List<Room> getRoomsByLocationId(String locationId) {
    return roomRepository.getRoomsByLocationId(locationId);
  }
}
