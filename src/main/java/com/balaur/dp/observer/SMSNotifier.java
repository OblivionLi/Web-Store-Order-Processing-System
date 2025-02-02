package com.balaur.dp.observer;

import com.balaur.model.Order;

public class SMSNotifier implements OrderObserver{
    @Override
    public void update(Order order) {
        System.out.println("Sending SMS notification for order " + order.getOrderId() + " with status: Order Placed");
        // in a real app, you would send an actual SMS here
    }
}
