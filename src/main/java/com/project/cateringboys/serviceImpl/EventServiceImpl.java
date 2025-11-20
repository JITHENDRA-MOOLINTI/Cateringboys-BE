package com.project.cateringboys.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cateringboys.exception.InvalidCreditintialsException;
import com.project.cateringboys.model.Booking;
import com.project.cateringboys.model.Event;
import com.project.cateringboys.model.User;
import com.project.cateringboys.model.enums.BookingStatus;
import com.project.cateringboys.model.enums.Role;
import com.project.cateringboys.model.enums.Status;
import com.project.cateringboys.repo.BookingRepo;
import com.project.cateringboys.repo.EventRepo;
import com.project.cateringboys.repo.UserRepo;
import com.project.cateringboys.service.EventService;


@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventRepo eventRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BookingRepo bookingRepo;
	
	@Override
	public Event createEvent(Event event,String email) {
		  User organizer=userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("User is not Found"));
		  if(!organizer.getRole().toString().equals("ADMIN")) {
			  throw new RuntimeException("Only Admin can create Events");
			  
		  }
		  event.setCreatedBy(organizer);
		  event.setStatus(Status.OPEN);
		 return  eventRepo.save(event);
		
	}

	@Override
	public Event updateEvent(Long eventId, Event updatedEvent) {
		Event event=eventRepo.findById(eventId).orElseThrow(()->new RuntimeException("Event is not found"));
		
//		if(!event.getCreatedBy().getId().equals(userId)) {
//			throw new RuntimeException("Not Authorized to Update this Event");
//		}
		
		event.setAmountPerBoy(updatedEvent.getAmountPerBoy());
		event.setDate(updatedEvent.getDate());
		event.setDescription(updatedEvent.getDescription());
		event.setRequiredboys(updatedEvent.getRequiredboys());
		event.setTime(updatedEvent.getTime());
		event.setTitle(updatedEvent.getTitle());
		event.setVenue(updatedEvent.getVenue());
		return eventRepo.save(event);
	}

	@Override
	public List<Event> getAllAvailableEvents(Long userId) {
		
		List<Booking> booked=bookingRepo.findByUserId(userId);
		 List<Long> bookedEventIds = booked.stream()
		            .map(b -> b.getEvent().getId())
		            .toList();
		 
		 if(bookedEventIds.isEmpty()) {
			 return eventRepo.findAll();
		 }
		 
		 return eventRepo.findByIdNotIn(bookedEventIds);
	}

	@Override
	public Event getEventById(Long eventId) {
		
		return eventRepo.findById(eventId).orElseThrow(()->new RuntimeException("Event is not Found"));
	}

	@Override
	public void cancelEvent(Long eventId, String email) {
		Event event=eventRepo.findById(eventId).orElseThrow(()->new RuntimeException("Event is not Found"));
		if(!event.getCreatedBy().getEmail().equals(email)) {
			throw new RuntimeException("You are not authorized to cancel the event");
		}
		
		event.setStatus(Status.CANCELLED);
		eventRepo.delete(event);
		
	}

	@Override
	public boolean isEventFull(Long EventId) {
		
		Event event=eventRepo.findById(EventId).orElseThrow(()->new RuntimeException("Event is not found"));
		
		return event.getBookings().size()>=event.getRequiredboys();
	}

	@Override
	public List<Event> getEventsByAdmin(String email) {
		  User admin = userRepo.findByEmail(email)
	                .orElseThrow(() -> new InvalidCreditintialsException("Admin not found"));
	        return eventRepo.findByCreatedBy(admin);
	}

	@Override
	public List<Event> getAllEvents() {
		
		return eventRepo.findAll();
	}

	
}
