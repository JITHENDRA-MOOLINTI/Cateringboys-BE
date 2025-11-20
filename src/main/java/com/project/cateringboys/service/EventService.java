package com.project.cateringboys.service;

import java.util.List;

import com.project.cateringboys.model.Event;
import com.project.cateringboys.model.User;

public interface EventService {
	
     Event createEvent(Event event,String email);
     Event updateEvent(Long eventId,Event event);
     List<Event> getAllAvailableEvents(Long userId);
     List<Event> getAllEvents();
     Event getEventById(Long Id);
     void cancelEvent(Long eventId,String email);
     boolean isEventFull(Long EventId);
     List<Event> getEventsByAdmin(String email);
}
