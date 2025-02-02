package com.balaur.dp.strategy;

import com.balaur.model.Order;

public class ShippingCostCalculator {
    private ShippingStrategy shippingStrategy;

    public ShippingCostCalculator(ShippingStrategy shippingStrategy) {
        this.shippingStrategy = shippingStrategy;
    }

    public double calculateShippingCost(Order order) {
        return shippingStrategy.calculateShippingCost(order);
    }
}
