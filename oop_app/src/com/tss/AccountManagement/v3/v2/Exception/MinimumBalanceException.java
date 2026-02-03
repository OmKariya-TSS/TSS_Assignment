package com.tss.AccountManagement.v3.v2.Exception;


public class MinimumBalanceException extends RuntimeException {
    private double balance;

    public MinimumBalanceException(double balance) {
        this.balance = balance;
    }

    @Override
    public String getMessage() {
        return "Withdrawal denied. Minimum balance of 500 must be maintained. Current balance: " + balance;
    }
}
