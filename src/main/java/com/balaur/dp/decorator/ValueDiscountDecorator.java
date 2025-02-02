package com.balaur.dp.decorator;

import com.balaur.model.Order;

public class ValueDiscountDecorator implements DiscountDecorator {
    private Order order;
    private double discountValue;

    public ValueDiscountDecorator(Order order, double discountValue) {
        this.order = order;
        this.discountValue = discountValue;
        order.addDiscountDecorator(this);
    }

    @Override
    public double getDiscountedAmount(double originalAmount) {
        return originalAmount - discountValue;
    }
}
