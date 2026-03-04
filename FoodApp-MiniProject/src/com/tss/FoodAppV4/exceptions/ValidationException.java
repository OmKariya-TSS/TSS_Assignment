package com.tss.FoodAppV4.exceptions;


public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}