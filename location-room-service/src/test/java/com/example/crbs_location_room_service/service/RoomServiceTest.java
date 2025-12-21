package com.example.crbs_location_room_service.service;

import com.example.crbs_location_room_service.model.Room;
import com.example.crbs_location_room_service.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

  @Mock
  private RoomRepository roomRepository; // Mock the dependency

  @InjectMocks
  private RoomService roomService; // Inject mock into the service

  @Test
  void testGetRoomById() {
    // Arrange
    Room mockRoom = new Room();
    mockRoom.setRoomId("RM101");
    when(roomRepository.getRoomById("RM101")).thenReturn(mockRoom);

    // Act
    Room result = roomService.getRoomById("RM101");

    // Assert
    assertEquals("RM101", result.getRoomId());
    verify(roomRepository, times(1)).getRoomById("RM101");
  }
}
