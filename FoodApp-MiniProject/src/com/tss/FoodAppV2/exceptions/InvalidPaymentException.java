package com.tss.FoodAppV2.exceptions;

public class InvalidPaymentException extends RuntimeException {
    public InvalidPaymentException(String method) {
        super("❌ Invalid payment method: " + method);
    }
}
