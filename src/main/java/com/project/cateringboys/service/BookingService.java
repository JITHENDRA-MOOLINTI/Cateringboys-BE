package com.project.cateringboys.service;

import java.util.List;

import com.project.cateringboys.model.Booking;
import com.project.cateringboys.model.enums.BookingStatus;
import com.project.cateringboys.model.enums.Role;

public interface BookingService {
	
    Booking bookEvent(Long eventId,Long userId);
    Booking getBookingById(Long BookingId);
    List<Booking> getBookingsByUser(Long userId);
    List<Booking> getBookingsByEvent(Long eventId);
    Booking updateBookingStatus(Long bookingId,BookingStatus status);
    void cancelBookingById(Long BookingId);
}
