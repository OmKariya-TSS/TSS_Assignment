package com.tss.FoodApp.exceptions;


public class InvoiceGenerationException extends RuntimeException {
    public InvoiceGenerationException(String message) {
        super(message);
    }
}