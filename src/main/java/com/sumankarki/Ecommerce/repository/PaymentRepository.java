package com.sumankarki.Ecommerce.repository;

import com.sumankarki.Ecommerce.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}