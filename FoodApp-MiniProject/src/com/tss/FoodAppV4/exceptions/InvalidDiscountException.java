package com.tss.FoodAppV4.exceptions;


public class InvalidDiscountException extends RuntimeException {
    public InvalidDiscountException(String message) {
        super(message);
    }
}