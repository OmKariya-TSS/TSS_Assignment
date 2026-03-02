package com.tss.FoodAppV3.exceptions;


public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}