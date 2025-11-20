package com.project.cateringboys.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.cateringboys.model.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="events")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Event {
      
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private String description;
	
	private LocalDate date;
	
	private LocalTime time;
	
    private String venue;
    
    private Integer requiredboys;
    
    private Double amountPerBoy;
    
    @Enumerated(EnumType.STRING)
    private Status status=Status.OPEN;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="created_by")
    private User createdBy;
    
    @OneToMany(mappedBy = "event")
    @JsonIgnoreProperties({"event"}) 
    private List<Booking> bookings;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    
    public Event() {
		// TODO Auto-generated constructor stub
	}

	public Event(String title, String description, LocalDate date, LocalTime time, String venue, Integer requiredboys,
			Double amountPerBoy, Status status, User createdBy) {
		super();
		this.title = title;
		this.description = description;
		this.date = date;
		this.time = time;
		this.venue = venue;
		this.requiredboys = requiredboys;
		this.amountPerBoy = amountPerBoy;
		this.status = status;
		this.createdBy = createdBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public Integer getRequiredboys() {
		return requiredboys;
	}

	public void setRequiredboys(Integer requiredboys) {
		this.requiredboys = requiredboys;
	}

	public Double getAmountPerBoy() {
		return amountPerBoy;
	}

	public void setAmountPerBoy(Double amountPerBoy) {
		this.amountPerBoy = amountPerBoy;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	 
    public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
	    return "Event [id=" + id + ", title=" + title + ", date=" + date + ", time=" + time +
	           ", venue=" + venue + ", requiredboys=" + requiredboys + ", amountPerBoy=" + amountPerBoy +
	           ", createdById=" + (createdBy != null ? createdBy.getId() : null) +
	           ", bookingsCount=" + (bookings != null ? bookings.size() : 0) +
	           ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

	
	
}
