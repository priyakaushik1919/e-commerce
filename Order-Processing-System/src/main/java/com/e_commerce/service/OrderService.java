package com.e_commerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.e_commerce.entity.Order;
import com.e_commerce.entity.Product;
import com.e_commerce.entity.Product2;
import com.e_commerce.entity.Product3;
import com.e_commerce.event.OrderEvent;
import com.e_commerce.repository.OrderRepository;
import com.e_commerce.repository.ProductRepository;
import com.e_commerce.repository.ProductRepository2;
import com.e_commerce.repository.ProductRepository3;

import jakarta.transaction.Transactional;

import java.math.BigDecimal;

@Service
public class OrderService {


    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ProductRepository2 productRepository2;
    
    @Autowired
    private ProductRepository3 productRepository3;
    
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    
    @Transactional
    public Order placeOrder(Long productId, String productType, int quantity) {
        BigDecimal pricePerUnit;
        
        // Determine which product table to use
        if ("product".equalsIgnoreCase(productType)) {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            pricePerUnit = product.getPrice();
        } 
        else if ("Product2".equalsIgnoreCase(productType)) {
            Product2 product2 = productRepository2.findById(productId).orElseThrow(() -> new RuntimeException("Product2 not found"));
            pricePerUnit = product2.getPrice();
        } else if ("Product3".equalsIgnoreCase(productType)) {
            Product3 product3 = productRepository3.findById(productId).orElseThrow(() -> new RuntimeException("Product3 not found"));
            pricePerUnit = product3.getPrice();
        } 
        else {
            throw new RuntimeException("Invalid product type");
        }

       
        // Calculate total price
        BigDecimal totalPrice = pricePerUnit.multiply(BigDecimal.valueOf(quantity));
        
        System.out.println("totalPrice" + totalPrice);

        // Save order
        Order order = new Order(productId, productType, quantity, totalPrice);
        order.setStatus("PENDING");
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setTotalPrice(totalPrice);
        
        order = orderRepository.save(order);

        // Publish an event for asynchronous order processing
        eventPublisher.publishEvent(new OrderEvent(this, order));

        return order;
    }

    
//    @Transactional
//    public Order placeOrder(Long productId, int quantity, BigDecimal pricePerUnit) {
//    	BigDecimal totalPrice = pricePerUnit.multiply(BigDecimal.valueOf(quantity));
//        Order order = new Order(productId, quantity, totalPrice);
//        order.setStatus("PENDING");
//        order = orderRepository.save(order);
//
//        // Publish an event for asynchronous order processing
//        eventPublisher.publishEvent(new OrderEvent(this, order));
//
//        return order;
//    }
    

   /* public Order placeOrder(Order order) {
        System.out.println("Placing a new order...");
        return orderRepository.save(order);
    }

    public Order simulatePayment(Long id) {
        System.out.println("Processing payment for order ID: {}: "+ id);
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isPresent()) {
            Order order = existingOrder.get();
            order.setStatus("PAID");
            orderRepository.save(order);
            System.out.println("Payment completed for order ID: {}: "+ id);
            return order;
        } else {
            System.out.println("Order with ID {} not found!: "+ id);
            throw new RuntimeException("Order not found");
        }
    }*/
}
