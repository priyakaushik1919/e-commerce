package com.e_commerce.event;

import org.springframework.context.ApplicationEvent;

import com.e_commerce.entity.Order;

public class OrderEvent extends ApplicationEvent {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Order order;

    public OrderEvent(Object source, Order order) {
        super(source);
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}

