package com.example.crbs_booking_service.repository;

import com.example.crbs.model.Booking;
import com.example.crbs.service.BookingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

  private final BookingService bookingService;

  public BookingController(BookingService bookingService) {
    this.bookingService = bookingService;
  }

  // endpoint to create a booking
  @PostMapping
  public Booking createBooking(@RequestBody Booking booking) {
    return bookingService.createBooking(booking);
  }

  @GetMapping
  public String status() {
    return "Ok";
  }
}
