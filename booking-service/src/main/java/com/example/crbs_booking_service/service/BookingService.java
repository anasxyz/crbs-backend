package com.example.crbs.service;

import com.example.crbs.model.Booking;
import com.example.crbs.repository.BookingRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

  private final BookingRepository bookingRepository;

  public BookingService(BookingRepository bookingRepository) {
    this.bookingRepository = bookingRepository;
  }

  public Booking createBooking(Booking booking) {
    // calculate final price (basePrice + operationalCost)
    // opertionalCost will come from somewher else
    // will probably have to change this later
    double finalPrice = booking.getBasePrice() + booking.getOperationalCost();
    booking.setFinalPrice(finalPrice);

    // set status to booked
    booking.setStatus("booked");

    // save to dynamodb
    return bookingRepository.addBooking(booking);
  }
}
