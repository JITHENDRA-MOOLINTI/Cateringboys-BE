package com.project.cateringboys.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.cateringboys.model.Booking;
import com.project.cateringboys.model.Event;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Long> {
     long countByEvent(Event event);
	 List<Booking> findByUserId(Long userId);
	 List<Booking> findByEventId(Long eventId);
} 
