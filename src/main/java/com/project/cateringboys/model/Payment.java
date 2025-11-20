package com.project.cateringboys.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.project.cateringboys.model.enums.PaymentMethod;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="payments")
public class Payment {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="booking_id")
	private Booking booking;
	
	private Double amount;
	
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	
	@CreationTimestamp
	private LocalDateTime paidAt;
	
    public Payment() {
		// TODO Auto-generated constructor stub
	}

	public Payment(Booking booking, Double amount, PaymentMethod paymentMethod) {
		super();
		this.booking = booking;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public LocalDateTime getPaidAt() {
		return paidAt;
	}

	public void setPaidAt(LocalDateTime paidAt) {
		this.paidAt = paidAt;
	}

	@Override
	public String toString() {
	    return "Payment [id=" + id + ", bookingId=" + (booking != null ? booking.getId() : null) +
	           ", amount=" + amount + ", paymentMethod=" + paymentMethod + ", paidAt=" + paidAt + "]";
	}

 
	
}
