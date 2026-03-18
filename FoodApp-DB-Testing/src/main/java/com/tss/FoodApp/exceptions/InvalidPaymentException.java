package com.tss.FoodApp.exceptions;

public class InvalidPaymentException extends RuntimeException {
    public InvalidPaymentException(String method) {
        super("❌ Invalid payment method: " + method);
    }
}
