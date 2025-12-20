package com.example.crbs_booking_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

  @GetMapping("/hello")
  public String hello() {
    return "Hello\n";
  }
}
