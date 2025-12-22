package com.example.crbs_booking_service.service;

import com.example.crbs_booking_service.model.Booking;
import com.example.crbs_booking_service.repository.BookingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

  @Mock
  private BookingRepository bookingRepository;

  @InjectMocks
  private BookingService bookingService;

  @Test
  void testCreateBooking() {
    Booking booking = new Booking();
    booking.setBasePrice(100.0);

    when(bookingRepository.addBooking(any(Booking.class))).thenAnswer(i -> i.getArguments()[0]);

    Booking result = bookingService.createBooking(booking);

    assertEquals(100.0, result.getBasePrice());
    assertEquals("booked", result.getStatus());
    verify(bookingRepository, times(1)).addBooking(any(Booking.class));
  }

  @Test
  void testGetAllBookings() {
    List<Booking> bookings = List.of(new Booking(), new Booking());
    when(bookingRepository.getAllBookings()).thenReturn(bookings);

    List<Booking> result = bookingService.getAllBookings();

    assertEquals(2, result.size());
    verify(bookingRepository, times(1)).getAllBookings();
  }

  @Test
  void testGetBookingById() {
    Booking booking = new Booking();
    booking.setBookingId("B-999");
    when(bookingRepository.getBookingById("B-999")).thenReturn(booking);

    Booking result = bookingService.getBookingById("B-999");

    assertEquals("B-999", result.getBookingId());
    verify(bookingRepository, times(1)).getBookingById("B-999");
  }
}
