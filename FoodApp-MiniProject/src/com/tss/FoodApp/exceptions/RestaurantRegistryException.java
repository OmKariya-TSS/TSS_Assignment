package com.tss.FoodApp.exceptions;


public class RestaurantRegistryException extends RuntimeException {
    public RestaurantRegistryException(String message) {
        super(message);
    }
}