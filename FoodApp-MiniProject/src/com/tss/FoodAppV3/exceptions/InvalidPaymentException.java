package com.tss.FoodAppV3.exceptions;

public class InvalidPaymentException extends RuntimeException {
    public InvalidPaymentException(String method) {
        super("❌ Invalid payment method: " + method);
    }
}
