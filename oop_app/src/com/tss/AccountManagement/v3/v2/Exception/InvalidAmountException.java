package com.tss.AccountManagement.v3.v2.Exception;


public class InvalidAmountException extends RuntimeException {
    private double amount;

    public InvalidAmountException(double amount) {
        this.amount = amount;
    }

    @Override
    public String getMessage() {
        return "Invalid transaction amount: " + amount;
    }
}
