package com.tss.AccountManagement.v1.model;

public class CurrentAccount extends AccountInheritence {
    public static final double MIN_BALANCE = 500;
    public CurrentAccount(String name, double balance) {
        super(name, balance);
    }
    @Override
    public double withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Enter a valid withdrawal amount");
            return 0;
        }
        if (amount > balance) {
            System.out.println("Insufficient balance");
            return 0;
        }
        if ((balance - amount) < MIN_BALANCE) {
            System.out.println("Withdrawal denied. Minimum balance of 500 must be maintained.");
            return 0;
        }
        balance -= amount;
        return amount;
    }
}
