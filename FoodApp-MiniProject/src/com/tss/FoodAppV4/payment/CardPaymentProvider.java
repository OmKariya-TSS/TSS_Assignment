package com.tss.FoodAppV4.payment;


public class CardPaymentProvider implements PaymentProvider {

    @Override
    public PaymentProcessor createPayment(String param) {

        String[] parts = param.split(",");

        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid card parameters");
        }

        return new CardPayment(
                parts[0].trim(),
                parts[1].trim(),
                parts[2].trim(),
                parts[3].trim()
        );
    }
}