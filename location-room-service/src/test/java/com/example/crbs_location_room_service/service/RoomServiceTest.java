package com.example.crbs_location_room_service.service;

import com.example.crbs_location_room_service.model.Room;
import com.example.crbs_location_room_service.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

  @Mock
  private RoomRepository roomRepository;

  @InjectMocks
  private RoomService roomService;

  @Test
  void testAddRoom() {
    Room room = new Room();
    room.setRoomId("RM1");
    when(roomRepository.addRoom(room)).thenReturn(room);

    Room result = roomService.addRoom(room);

    assertEquals("RM1", result.getRoomId());
    verify(roomRepository, times(1)).addRoom(room);
  }

  @Test
  void testGetAllRooms() {
    List<Room> rooms = List.of(new Room(), new Room());
    when(roomRepository.getAllRooms()).thenReturn(rooms);

    List<Room> result = roomService.getAllRooms();

    assertEquals(2, result.size());
  }

  @Test
  void testGetRoomById() {
    Room room = new Room();
    room.setRoomId("RM-101");
    when(roomRepository.getRoomById("RM-101")).thenReturn(room);

    Room result = roomService.getRoomById("RM-101");

    assertEquals("RM-101", result.getRoomId());
  }

  @Test
  void testGetRoomsByLocationId() {
    String locId = "LOC-A";
    List<Room> rooms = List.of(new Room());
    when(roomRepository.getRoomsByLocationId(locId)).thenReturn(rooms);

    List<Room> result = roomService.getRoomsByLocationId(locId);

    assertEquals(1, result.size());
    verify(roomRepository, times(1)).getRoomsByLocationId(locId);
  }
}
