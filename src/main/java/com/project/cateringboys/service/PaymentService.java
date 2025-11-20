package com.project.cateringboys.service;

import java.util.List;

import com.project.cateringboys.model.Payment;
import com.project.cateringboys.model.enums.PaymentMethod;


public interface PaymentService {
       Payment makePayment(Long bookingId,Double amount,PaymentMethod paymentMethod);
       Payment getPaymentByBooking(Long bookingId);
}
