package com.project.cateringboys.serviceImpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cateringboys.model.Booking;
import com.project.cateringboys.model.Payment;
import com.project.cateringboys.model.enums.AttendanceStatus;
import com.project.cateringboys.model.enums.BookingStatus;
import com.project.cateringboys.model.enums.PaymentMethod;
import com.project.cateringboys.repo.BookingRepo;
import com.project.cateringboys.repo.PaymentRepo;
import com.project.cateringboys.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	private BookingRepo bookingRepo;
	
	@Autowired
	private PaymentRepo paymentRepo;

	@Override
	public Payment makePayment(Long bookingId, Double amount, PaymentMethod paymentMethod) {
		Booking booking=bookingRepo.findById(bookingId).orElseThrow(()->new RuntimeException("booking is not found"));
//		if(booking.getStatus()!=AttendanceStatus.PRESENT) {
//			throw new RuntimeException("Payment allowed only after attending the event");
//		}
		
		Payment payment=new Payment();
		payment.setBooking(booking);
		payment.setAmount(amount);
		payment.setPaidAt(LocalDateTime.now());
		payment.setPaymentMethod(paymentMethod);
		
		booking.setStatus(BookingStatus.PAID);
		bookingRepo.save(booking);
		
		return paymentRepo.save(payment);
	}

	@Override
	public Payment getPaymentByBooking(Long bookingId) {
		 Booking booking = bookingRepo.findById(bookingId)
	                .orElseThrow(() -> new RuntimeException("Booking not found"));
	        return paymentRepo.findByBooking(booking)
	                .orElseThrow(() -> new RuntimeException("Payment not found for this booking"));
		
	}

}
