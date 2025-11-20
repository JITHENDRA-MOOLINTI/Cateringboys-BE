package com.project.cateringboys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cateringboys.model.Event;
import com.project.cateringboys.service.EventService;

@RestController
@RequestMapping("/cateringboys")
@CrossOrigin(origins = "*")
public class EventController {
        
	  @Autowired
	  private EventService eventService;
	  
	  @GetMapping("/user/events/{userId}")
	  public ResponseEntity<List<Event>> getAllEvents(@PathVariable Long userId){
		  return ResponseEntity.ok(eventService.getAllAvailableEvents(userId));
	  }
	  
	  
	  @GetMapping("/{eventId}")
	  public ResponseEntity<?> getEventById(@PathVariable Long eventId){
		 try {
			 return ResponseEntity.ok(eventService.getEventById(eventId));
		 }catch(RuntimeException e) {
			 return ResponseEntity.badRequest().body(e.getMessage());
		 }
	  }
}
