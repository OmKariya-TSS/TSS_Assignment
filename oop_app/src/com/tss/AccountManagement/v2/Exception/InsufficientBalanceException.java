package com.tss.AccountManagement.v2.Exception;

public class InsufficientBalanceException extends Exception {
    private final double balance;
    private final double amount;

    public InsufficientBalanceException(double balance, double amount) {
        super("Insufficient funds! Current balance: " + balance + ", Attempted withdrawal: " + amount);
        this.balance = balance;
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public double getAmount() {
        return amount;
    }
}
