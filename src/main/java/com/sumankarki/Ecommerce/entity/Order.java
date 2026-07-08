package com.sumankarki.Ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order" , fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderItem> orderItemsList;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
