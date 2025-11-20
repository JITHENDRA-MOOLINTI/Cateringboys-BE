package com.project.cateringboys.model;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.cateringboys.model.enums.AttendanceStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="attendance")
public class Attendance {
     
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="booking_id")
	@JsonIgnore
	private Booking booking;
	
	@Enumerated(EnumType.STRING)
	private AttendanceStatus attendanceStatus;
	
	
	private LocalTime attendedAt;
	
	public Attendance() {
		// TODO Auto-generated constructor stub
	}

	public Attendance(Booking booking, AttendanceStatus attendanceStatus) {
		super();
		this.booking = booking;
		this.attendanceStatus =attendanceStatus;
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


	public AttendanceStatus getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(AttendanceStatus attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}


	public LocalTime getAttendedAt() {
		return attendedAt;
	}

	public void setAttendedAt(LocalTime attendedAt) {
		this.attendedAt = attendedAt;
	}

	@Override
	public String toString() {
	    return "Attendance [id=" + id + ", bookingId=" + (booking != null ? booking.getId() : null) +
	           ", attended=" + attendanceStatus + ", markedAt=" + attendedAt + "]";
	}

}
