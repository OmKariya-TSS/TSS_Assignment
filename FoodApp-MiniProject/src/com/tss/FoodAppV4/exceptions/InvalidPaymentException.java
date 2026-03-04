package com.tss.FoodAppV4.exceptions;

public class InvalidPaymentException extends RuntimeException {
    public InvalidPaymentException(String method) {
        super("❌ Invalid payment method: " + method);
    }
}
