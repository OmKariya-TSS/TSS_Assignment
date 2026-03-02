package com.tss.FoodAppV2.exceptions;


public class InvalidDiscountException extends RuntimeException {
    public InvalidDiscountException(String message) {
        super(message);
    }
}