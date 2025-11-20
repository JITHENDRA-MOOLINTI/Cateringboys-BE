package com.project.cateringboys.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.cateringboys.model.Attendance;
import com.project.cateringboys.model.Booking;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Long> {

	Optional<Attendance> findByBooking(Booking booking);

}
