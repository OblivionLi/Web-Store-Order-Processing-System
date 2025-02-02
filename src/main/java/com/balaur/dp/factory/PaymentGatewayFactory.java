package com.balaur.dp.factory;

import com.balaur.dp.adapter.PaymentGateway;
import com.balaur.dp.adapter.PaypalPaymentGateway;
import com.balaur.dp.adapter.StripePaymentGateway;
import com.balaur.dp.factory.enums.GatewayType;

public class PaymentGatewayFactory {
    public static PaymentGateway createPaymentGateway(GatewayType gatewayType) {
        return switch (gatewayType) {
            case PAYPAL -> new PaypalPaymentGateway();
            case STRIPE -> new StripePaymentGateway();
            default -> throw new IllegalArgumentException("Invalid payment gateway type: " + gatewayType);
        };
    }
}
