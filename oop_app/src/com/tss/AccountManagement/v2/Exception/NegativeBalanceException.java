package com.tss.AccountManagement.v2.Exception;


public class NegativeBalanceException extends RuntimeException {
    private double balance;

    public NegativeBalanceException(double balance) {
        this.balance = balance;
    }

    @Override
    public String getMessage() {
        return "Initial balance cannot be negative: " + balance;
    }
}
