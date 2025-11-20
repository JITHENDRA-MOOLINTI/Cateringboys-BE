package com.project.cateringboys.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.cateringboys.model.Booking;
import com.project.cateringboys.model.enums.Role;
import com.project.cateringboys.service.BookingService;

@RestController
@RequestMapping("/cateringboys/user")
@CrossOrigin(origins="*")
public class BookingController {
       
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/{eventId}/{userId}")
	public ResponseEntity<?> bookEvent(@PathVariable Long eventId,@PathVariable Long userId){
		try {
			Booking booking=bookingService.bookEvent(eventId, userId);
			return ResponseEntity.ok(booking);
		}catch(RuntimeException e) {
			return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));
		}
	}
	
	@DeleteMapping("/{bookingId}")
	public ResponseEntity<?> cancelBooking(@PathVariable Long bookingId){
		try {
			bookingService.cancelBookingById(bookingId);
			return ResponseEntity.ok("Successfully cancel The Event");
		}catch(RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getBookingsByUser(@PathVariable Long userId){
		try {
			return ResponseEntity.ok(bookingService.getBookingsByUser(userId));
		}catch(RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	
}
