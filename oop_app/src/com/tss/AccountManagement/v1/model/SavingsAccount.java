package com.tss.AccountManagement.v1.model;

public class SavingsAccount extends AccountInheritence {
    public static final int OFFER_RATE = 8;
    public SavingsAccount(String name, double balance) {
        super(name, balance);
    }
    @Override
    public double deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Please enter a valid deposit amount");
            return 0;
        }
        if (amount > 50000) {
            double bonus = amount * OFFER_RATE / 100;
            super.deposit(amount + bonus);
            System.out.println("Offer applied! Bonus added: " + bonus);
        } else {
            super.deposit(amount);
        }
        return amount;
    }
}
