package com.tss.Structural.proxy;

class RealBankAccount implements BankAccount {

    private double balance;

    public RealBankAccount(double balance) {
        this.balance = balance;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdraw successful. Remaining balance: " + balance);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    @Override
    public void getBalance() {
        System.out.println("Current balance: " + balance);
    }
}

