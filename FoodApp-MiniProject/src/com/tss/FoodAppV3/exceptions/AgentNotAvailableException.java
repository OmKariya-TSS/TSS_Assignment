package com.tss.FoodAppV3.exceptions;

public class AgentNotAvailableException extends RuntimeException {

    public AgentNotAvailableException() {
        super("No delivery agent available at the moment.");
    }

    public AgentNotAvailableException(String message) {
        super(message);
    }
}