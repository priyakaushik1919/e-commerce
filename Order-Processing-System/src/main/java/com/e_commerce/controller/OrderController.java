package com.e_commerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.e_commerce.entity.Order;
import com.e_commerce.service.OrderService;



@RestController
@RequestMapping("/api/orders")
public class OrderController {


    @Autowired
    private OrderService orderService;
    
    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(
            @RequestParam("productId") Long productId,
            @RequestParam("productType") String productType,
            @RequestParam("quantity") int quantity) {
        Order order = orderService.placeOrder(productId, productType, quantity);
        return ResponseEntity.ok(order);
    
    }



    /*@PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        System.out.println("Placing order with status: {}: " +order.getStatus());
        Order placedOrder = orderService.placeOrder(order);
        System.out.println("Order placed successfully with ID: {}: "+ placedOrder.getId());
        return ResponseEntity.ok(placedOrder);
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<Order> simulatePayment(@PathVariable Long id) {
        System.out.println("Simulating payment for order ID: {}: "+ id);
        Order updatedOrder = orderService.simulatePayment(id);
        System.out.println("Payment processed for order ID: {}: "+ id);
        return ResponseEntity.ok(updatedOrder);
    }*/
}


