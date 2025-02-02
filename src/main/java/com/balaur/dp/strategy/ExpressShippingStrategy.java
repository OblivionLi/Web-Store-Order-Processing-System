package com.balaur.dp.strategy;

import com.balaur.model.Order;

public class ExpressShippingStrategy implements ShippingStrategy {
    @Override
    public double calculateShippingCost(Order order) {
        // standard shipping cost (10% of the total)
        return order.getTotalAmount() * 0.10;
    }
}
