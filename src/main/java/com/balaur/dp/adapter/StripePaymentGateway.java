package com.balaur.dp.adapter;

public class StripePaymentGateway implements PaymentGateway {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " via Stripe...");
        // in real implementation, you would call stripe's API here
    }
}
