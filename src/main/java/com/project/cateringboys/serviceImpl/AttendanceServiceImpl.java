package com.project.cateringboys.serviceImpl;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cateringboys.exception.ResourceNotFoundException;
import com.project.cateringboys.model.Attendance;
import com.project.cateringboys.model.Booking;
import com.project.cateringboys.model.enums.AttendanceStatus;
import com.project.cateringboys.model.enums.BookingStatus;
import com.project.cateringboys.repo.AttendanceRepo;
import com.project.cateringboys.repo.BookingRepo;
import com.project.cateringboys.service.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService{
    
	@Autowired
	private AttendanceRepo attendanceRepo;
	
	
	@Autowired
	private BookingRepo bookingRepo;
	
	
	@Override
	public void markAttendance(Long bookingId, AttendanceStatus attendanceStatus,String attendedAt) {
	  
	    Booking booking = bookingRepo.findById(bookingId)
	            .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));

	    
	    Attendance attendance = attendanceRepo.findByBooking(booking).orElse(new Attendance());  

	 
	    attendance.setAttendanceStatus(attendanceStatus);
	    attendance.setAttendedAt(LocalTime.parse(attendedAt));
	    attendance.setBooking(booking);
	    attendanceRepo.save(attendance);
        booking.setAttendance(attendance);
        bookingRepo.save(booking);
     
	}


	@Override
	public Attendance getAttendanceByBooking(Long BookingId) {
		
		     Booking booking=bookingRepo.findById(BookingId).orElseThrow(()->new RuntimeException("Booking not found"));
		     return attendanceRepo.findByBooking(booking).orElseThrow(()->new RuntimeException("Attendance not Found"));
		  
	}

}
