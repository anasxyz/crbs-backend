package com.example.crbs_booking_service.controller;

import com.example.crbs_booking_service.model.Booking;
import com.example.crbs_booking_service.service.BookingService;

import java.util.List;

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

  @GetMapping("/status")
  public String status() {
    return "Ok\n";
  }
}
