package com.project.cateringboys.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.cateringboys.model.Booking;
import com.project.cateringboys.model.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment,Long>{

	Optional<Payment> findByBooking(Booking booking);

}
