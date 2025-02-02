package com.balaur.dp.strategy;

import com.balaur.model.Order;

public class StandardShippingStrategy implements ShippingStrategy {
    @Override
    public double calculateShippingCost(Order order) {
        // standard shipping cost (5% of the total)
        return order.getTotalAmount() * 0.05;
    }
}
