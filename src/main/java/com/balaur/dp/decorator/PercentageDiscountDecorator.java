package com.balaur.dp.decorator;

import com.balaur.model.Order;

public class PercentageDiscountDecorator implements DiscountDecorator {
    private Order order;
    private double percentage;

    public PercentageDiscountDecorator(Order order, double percentage) {
        this.order = order;
        this.percentage = percentage;
        order.addDiscountDecorator(this);
    }

    @Override
    public double getDiscountedAmount(double originalAmount) {
        double discountAmount = originalAmount * (percentage / 100.0);
        return originalAmount - discountAmount;
    }
}
