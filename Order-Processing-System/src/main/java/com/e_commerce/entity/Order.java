package com.e_commerce.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal totalPrice;
    
    private String status;
    private Long productId;
    private int quantity;
    private String productType; // FIELD to track the product table
    private LocalDateTime createdAt;
    
    public Order() {
        this.createdAt = LocalDateTime.now();
        this.status = "PENDING";
        this.totalPrice = BigDecimal.ZERO;
    }

    public Order(Long productId, String productType, int quantity, BigDecimal totalPrice) {
        this.productId = productId;
        this.productType = productType;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.createdAt = LocalDateTime.now();
        this.status = "PENDING";
    }

   
}

