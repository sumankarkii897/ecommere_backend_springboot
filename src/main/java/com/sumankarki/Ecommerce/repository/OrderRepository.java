package com.sumankarki.Ecommerce.repository;

import com.sumankarki.Ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}