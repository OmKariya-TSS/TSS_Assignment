package com.tss.FoodAppV2.exceptions;


public class InvoiceGenerationException extends RuntimeException {
    public InvoiceGenerationException(String message) {
        super(message);
    }
}