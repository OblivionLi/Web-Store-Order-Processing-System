package com.balaur.dp.observer;

import com.balaur.model.Order;


public class EmailNotifier implements OrderObserver {
    @Override
    public void update(Order order) {
        System.out.println("Sending Email notification for order " + order.getOrderId() + " with status: Order Placed");
        // in a real app, you would send an actual email here
    }
}
