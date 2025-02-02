package com.balaur.model;

import com.balaur.dp.adapter.PaymentGateway;
import com.balaur.dp.decorator.DiscountDecorator;
import com.balaur.dp.factory.PaymentGatewayFactory;
import com.balaur.dp.factory.enums.GatewayType;
import com.balaur.dp.observer.OrderObserver;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private String orderId;
    private ShoppingCart cart;
    private LocalDateTime orderDate;
    private double totalAmount;
    private PaymentGateway paymentGateway;
    private List<OrderObserver> observers;
    private String orderStatus;
    private double shippingCost;
    private List<DiscountDecorator> discountDecorators;


    public Order(ShoppingCart cart, GatewayType paymentGateway) {
        this.orderId = UUID.randomUUID().toString();
        this.orderDate = LocalDateTime.now();
        this.totalAmount = cart.getTotalPrice();
        this.cart = cart;
        this.paymentGateway = PaymentGatewayFactory.createPaymentGateway(paymentGateway);
        this.observers = new ArrayList<>();
        this.orderStatus = "Order Placed";
        setShippingCost(0);
        this.discountDecorators = new ArrayList<>();
    }

    public String getOrderId() {
        return orderId;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public double getTotalAmount() {
        double discountedAmount = totalAmount;
        for (DiscountDecorator decorator : discountDecorators) {
            discountedAmount = decorator.getDiscountedAmount(discountedAmount);
        }
        return discountedAmount;    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void processPayment() {
        paymentGateway.processPayment(totalAmount);
    }

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (OrderObserver observer : observers) {
            observer.update(this);
        }
    }

    public void setOrderStatus(String status) {
        this.orderStatus = status;
        notifyObservers();
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void addDiscountDecorator(DiscountDecorator discountDecorator) {
        discountDecorators.add(discountDecorator);
    }
}
