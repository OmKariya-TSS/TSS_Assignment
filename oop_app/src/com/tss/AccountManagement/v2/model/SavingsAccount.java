package com.tss.AccountManagement.v2.model;

import com.tss.AccountManagement.v2.Exception.InvalidAmountException;
import com.tss.AccountManagement.v2.Exception.InvalidNameException;
import com.tss.AccountManagement.v2.Exception.NegativeBalanceException;

public class SavingsAccount extends Account {

    public static final int OFFER_RATE = 8;

    public SavingsAccount(String name, double balance)
            throws NegativeBalanceException, InvalidNameException {
        super(name, balance);
    }

    @Override
    public double deposit(double amount) throws InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException(amount);
        }

        if (amount > 50000) {
            double bonus = amount * OFFER_RATE / 100;
            super.deposit(amount + bonus);
        } else {
            super.deposit(amount);
        }

        return amount;
    }
}
