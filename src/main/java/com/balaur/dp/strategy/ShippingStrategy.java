package com.balaur.dp.strategy;

import com.balaur.model.Order;

public interface ShippingStrategy {
    double calculateShippingCost(Order order);
}
