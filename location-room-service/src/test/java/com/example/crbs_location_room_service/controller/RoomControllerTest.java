package com.example.crbs_location_room_service.controller;

import com.example.crbs_location_room_service.model.Room;
import com.example.crbs_location_room_service.service.RoomService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomControllerTest {

  @Mock
  private RoomService roomService;

  @InjectMocks
  private RoomController roomController;

  @Test
  void testGetAllRooms() {
    // Arrange
    List<Room> mockRooms = List.of(new Room(), new Room());
    when(roomService.getAllRooms()).thenReturn(mockRooms);

    // Act
    List<Room> result = roomController.getAllRooms();

    // Assert
    assertEquals(2, result.size());
  }

  @Test
  void testCreateRoom() {
    // Arrange
    Room room = new Room();
    room.setRoomId("RM101");
    when(roomService.addRoom(room)).thenReturn(room);

    // Act
    Room result = roomController.createRoom(room);

    // Assert
    assertEquals("RM101", result.getRoomId());
  }

  @Test
  void testGetRoomById() {
    // Arrange
    Room room = new Room();
    room.setRoomId("RM101");
    when(roomService.getRoomById("RM101")).thenReturn(room);

    // Act
    Room result = roomController.getRoomById("RM101");

    // Assert
    assertEquals("RM101", result.getRoomId());
  }

  @Test
  void testGetRoomsByLocationId() {
    // Arrange
    String locId = "LOC-123";
    List<Room> mockRooms = List.of(new Room());
    when(roomService.getRoomsByLocationId(locId)).thenReturn(mockRooms);

    // Act
    List<Room> result = roomController.getRoomsByLocationId(locId);

    // Assert
    assertEquals(1, result.size());
  }
}
