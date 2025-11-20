package com.project.cateringboys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.cateringboys.model.Attendance;
import com.project.cateringboys.model.Event;
import com.project.cateringboys.model.enums.AttendanceStatus;
import com.project.cateringboys.repo.AttendanceRepo;
import com.project.cateringboys.service.AttendanceService;
import com.project.cateringboys.service.BookingService;
import com.project.cateringboys.service.EventService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/cateringboys/admin")
@CrossOrigin(origins = "*")
public class AdminEventController {
      
	 @Autowired
	 private EventService eventService;
	 
	 @Autowired
	 private AttendanceService attendanceService;
	 
	 @Autowired
	 private BookingService bookingService;
	 
	 
	 @PostMapping
	 public ResponseEntity<?> createEvent(@RequestBody Event event,@RequestParam String email){
		 try {
			 return ResponseEntity.ok(eventService.createEvent(event,email));
		 }catch(RuntimeException e) {
			 return ResponseEntity.badRequest().body(e.getMessage());
		 }
	 }
	 
	 @PutMapping("/update/{eventId}")
	 public ResponseEntity<?> updateEvent(@PathVariable Long eventId,@RequestBody Event event){
		 try {
		      Event event1=eventService.updateEvent(eventId, event);
		      return ResponseEntity.ok(event1);
		 }catch(RuntimeException e) {
			 return ResponseEntity.badRequest().body(e.getMessage());
		 }
		 
	 }
	 
	 @DeleteMapping("/{eventId}")
	 public ResponseEntity<?> cancelEvent(@PathVariable Long eventId,@RequestParam String email){
		 try {
			 eventService.cancelEvent(eventId, email);
			 return ResponseEntity.ok("Event Cancelled Successfully");
		 }catch(RuntimeException e) {
			 return ResponseEntity.badRequest().body(e.getMessage());
		 }
		
	 }
	 
	 @GetMapping
	 public ResponseEntity<List<Event>> getAllEvents(){
		 return ResponseEntity.ok(eventService.getAllEvents());
	 }
	 
	 @GetMapping("/{eventId}")
	 public ResponseEntity<List<?>> getBookingsByEventId(@PathVariable Long eventId){
		 return ResponseEntity.ok(bookingService.getBookingsByEvent(eventId));
	 }	
	 
	 @RequestMapping(value = "/{bookingId}",method= {RequestMethod.POST,RequestMethod.PUT})
	 public  void markAttendance(@PathVariable Long bookingId,@RequestParam AttendanceStatus attendanceStatus,@RequestParam String attendedAt){
		  attendanceService.markAttendance(bookingId, attendanceStatus,attendedAt);
	 } 
	 
	 @GetMapping("/events")
	 public ResponseEntity<List<Event>> getEventsByAdminId(@RequestParam String email){
		 return ResponseEntity.ok(eventService.getEventsByAdmin(email));
	 }
}
