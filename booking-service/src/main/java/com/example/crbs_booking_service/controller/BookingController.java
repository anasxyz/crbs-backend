package com.example.crbs_booking_service.controller;

import com.example.crbs_booking_service.model.Booking;
import com.example.crbs_booking_service.service.BookingService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookingController {

  private final BookingService bookingService;

  public BookingController(BookingService bookingService) {
    this.bookingService = bookingService;
  }

  // endpoint to create a booking
  @PostMapping("/booking/new")
  public Booking createBooking(@RequestBody Booking booking) {
    return bookingService.createBooking(booking);
  }

  // endpoint to get booking by id
  @GetMapping("/booking/{bookingId}")
  public Booking getBookingById(@PathVariable String bookingId) {
    return bookingService.getBookingById(bookingId);
  }

  // endpoint to get all bookings
  @GetMapping("/booking/all")
  public List<Booking> getAllBookings() {
    return bookingService.getAllBookings();
  }

  // endpoint to get bookings by userId
  @GetMapping("/booking/user/{userId}")
  public List<Booking> getBookingsByUserId(@PathVariable String userId) {
    return bookingService.getBookingsByUserId(userId);
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
