package com.project.cateringboys.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.cateringboys.model.enums.BookingStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="bookings")
public class Booking {
      
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name="event_id")
	  @JsonIgnoreProperties({"bookings"}) 
	  private Event event;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name="user_id")
	  private User user;
	  
	  @Enumerated(EnumType.STRING)
	  private BookingStatus status;
	  
	  @CreationTimestamp
	  private LocalDateTime bookedAt;
	  
	  @OneToOne(mappedBy = "booking",fetch = FetchType.LAZY)
	  private Payment payment;
	  
	  @OneToOne(mappedBy = "booking",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	  private Attendance attendance;
	  
	  public Booking() {
		// TODO Auto-generated constructor stub
	}

	public Booking(Event event, User user, BookingStatus status) {
		super();
		this.event = event;
		this.user = user;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	public LocalDateTime getBookedAt() {
		return bookedAt;
	}

	public void setBoookedAt(LocalDateTime bookedAt) {
		this.bookedAt = bookedAt;
	}


	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Attendance getAttendance() {
		return attendance;
	}

	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}

	@Override
	public String toString() {
	    return "Booking [id=" + id + ", eventId=" + (event != null ? event.getId() : null) +
	           ", userId=" + (user != null ? user.getId() : null) +
	           ", bookedAt=" + bookedAt ;
	}

  
}
