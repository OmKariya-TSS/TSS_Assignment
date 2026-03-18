package com.tss.FoodApp.exceptions;


public class InvalidOrderOperationException extends RuntimeException {
    public InvalidOrderOperationException(String message) {
        super(message);
    }
}