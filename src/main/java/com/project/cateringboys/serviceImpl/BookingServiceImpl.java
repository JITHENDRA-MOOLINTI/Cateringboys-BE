package com.project.cateringboys.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cateringboys.exception.ResourceNotFoundException;
import com.project.cateringboys.model.Attendance;
import com.project.cateringboys.model.Booking;
import com.project.cateringboys.model.Event;
import com.project.cateringboys.model.User;
import com.project.cateringboys.model.enums.AttendanceStatus;
import com.project.cateringboys.model.enums.BookingStatus;
import com.project.cateringboys.model.enums.Role;
import com.project.cateringboys.model.enums.Status;
import com.project.cateringboys.repo.AttendanceRepo;
import com.project.cateringboys.repo.BookingRepo;
import com.project.cateringboys.repo.EventRepo;
import com.project.cateringboys.repo.UserRepo;
import com.project.cateringboys.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	
	@Autowired
	private BookingRepo bookingRepo;
	
	@Autowired
	private EventRepo eventRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private AttendanceRepo attendanceRepo; 

	@Override
	public Booking bookEvent(Long eventId, Long userId) {
		Event event=eventRepo.findById(eventId).orElseThrow(()->new ResourceNotFoundException("Event  ID " + eventId + " not found"));
		User user=userRepo.findById(userId).orElseThrow(()->new RuntimeException("User is not Found"));
		
		long bookedCount=bookingRepo.countByEvent(event);
	    if(bookedCount>=event.getRequiredboys()) {
	    	throw new RuntimeException("Event is Fully Booked");
	    }
	    if(user.getRole()!=Role.USER) {
	    	throw new RuntimeException("Admin Can't Book The Event");
	    }
	    
	    if(bookedCount==event.getRequiredboys()-1) {
	    	 event.setStatus(Status.FULL);
	    }
	    
	    Booking booking=new Booking(event,user,BookingStatus.BOOKED);
	    booking.setBoookedAt(LocalDateTime.now());
	    Booking booked=bookingRepo.save(booking);
	    
	    Attendance attendance=new Attendance();
	    attendance.setAttendanceStatus(AttendanceStatus.ABSENT);
	    attendance.setBooking(booking);
	    attendanceRepo.save(attendance);
		return booked;
	}

	@Override
	public Booking getBookingById(Long BookingId) {
		return bookingRepo.findById(BookingId).orElseThrow(()->new RuntimeException("booking is not found"));
	}

	@Override
	public List<Booking> getBookingsByUser(Long userId) {
		    return bookingRepo.findByUserId(userId);
	}

	@Override
	public List<Booking> getBookingsByEvent(Long eventId) {
		return bookingRepo.findByEventId(eventId);
	}

	@Override
	public Booking updateBookingStatus(Long bookingId, BookingStatus status) {
		Booking booking=bookingRepo.findById(bookingId).orElseThrow(()->new RuntimeException("Booking is not Found"));
	    booking.setStatus(status);
		return bookingRepo.save(booking);
	}

	@Override
	public void cancelBookingById(Long BookingId) {
		Booking booking=bookingRepo.findById(BookingId).orElseThrow(()->new RuntimeException("Booking is not Found"));
		booking.setStatus(BookingStatus.CANCELLED);
		bookingRepo.deleteById(BookingId);;
		
	}
}
