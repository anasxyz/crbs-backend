package com.example.crbs_booking_service.controller;

import com.example.crbs_booking_service.model.Booking;
import com.example.crbs_booking_service.service.BookingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {
  @Mock
  private BookingService bookingService;

  @InjectMocks
  private BookingController bookingController;

  @Test
  void testCreateBooking() {
    Booking booking = new Booking();
    booking.setBookingId("123");
    when(bookingService.createBooking(booking)).thenReturn(booking);

    Booking result = bookingController.createBooking(booking);

    assertEquals("123", result.getBookingId());
  }

  @Test
  void testGetBookingById() {
    Booking booking = new Booking();
    booking.setBookingId("123");
    when(bookingService.getBookingById("123")).thenReturn(booking);

    Booking result = bookingController.getBookingById("123");

    assertEquals("123", result.getBookingId());
  }

  @Test
  void testGetAllBookings() {
    List<Booking> mockBookings = List.of(new Booking(), new Booking());
    when(bookingService.getAllBookings()).thenReturn(mockBookings);

    List<Booking> result = bookingController.getAllBookings();

    assertEquals(2, result.size());
  }
}
