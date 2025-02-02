package com.balaur.dp.adapter;

public class PaypalPaymentGateway implements PaymentGateway {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " via Paypal...");
        // in real implementation, you would call paypal's API here
    }
}
