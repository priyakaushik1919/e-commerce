package com.e_commerce.event;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.e_commerce.entity.Order;
import com.e_commerce.repository.OrderRepository;

@Component
public class OrderEventListener {

    @Autowired
    private OrderRepository orderRepository;

    @Async
    @EventListener
    public void handleOrderEvent(OrderEvent event) {
        Order order = event.getOrder();
        System.out.println("Processing order asynchronously: " + order.getId());

        try {
            // Simulate order processing delay
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        order.setStatus("COMPLETED");
        orderRepository.save(order);
        System.out.println("Order processing completed: " + order.getId());
    }
}

