package com.tss.AccountManagement.v3.v2.model;

import com.tss.AccountManagement.v3.v2.Exception.*;

public class CurrentAccount extends Account {

    public static final double MIN_BALANCE = 500;

    public CurrentAccount(String name, double balance)
            throws NegativeBalanceException, InvalidNameException {
        super(name, balance);
    }

    @Override
    public double withdraw(double amount)
            throws InvalidAmountException,
            InsufficientBalanceException, MinimumBalanceException {

        if (amount <= 0) {
            throw new InvalidAmountException(amount);
        }

        if (amount > balance) {
            throw new InsufficientBalanceException(balance, amount);
        }

        if ((balance - amount) < MIN_BALANCE) {
            throw new MinimumBalanceException(balance);
        }

        balance -= amount;
        return amount;
    }
}
