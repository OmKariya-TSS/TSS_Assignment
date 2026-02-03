package com.tss.AccountManagement.v3.v2.model;

import com.tss.AccountManagement.v3.v2.Exception.*;

import java.util.Random;

public abstract class Account {

    public static int idCounter = 1;
    private int id;
    private int accountNumber;
    private String name;
    protected double balance;

    public static void printHeader() {
        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-10s %-15s %-15s %-10s\n",
                "ID", "Account No.", "Name", "Balance");
        System.out.println("-------------------------------------------------------------");
    }

    private Random random = new Random();

    private int generateAccId() {
        return random.nextInt(9000) + 1000;
    }

    public Account(String name, double balance)
            throws NegativeBalanceException, InvalidNameException {

        if (balance < 0) {
            throw new NegativeBalanceException(balance);
        }
        if (name == null || name.isEmpty()) {
            throw new InvalidNameException();
        }

        this.id = idCounter++;
        this.accountNumber = generateAccId();
        this.name = name;
        this.balance = balance;

        System.out.println("Account Id : " + id);
        System.out.println("Account Number : " + accountNumber);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setName(String name) throws InvalidNameException {
        if (name == null || name.isEmpty()) {
            throw new InvalidNameException();
        }
        this.name = name;
    }

    public void setBalance(double balance) throws NegativeBalanceException {
        if (balance < 0) {
            throw new NegativeBalanceException(balance);
        }
        this.balance = balance;
    }

    public double deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException(amount);
        }
        balance += amount;
        return amount;
    }

    public double withdraw(double amount)
            throws InvalidAmountException, InsufficientBalanceException, MinimumBalanceException {

        if (amount <= 0) {
            throw new InvalidAmountException(amount);
        }
        if (amount > balance) {
            throw new InsufficientBalanceException(balance, amount);
        }
        balance -= amount;
        return amount;
    }

    @Override
    public String toString() {
        return String.format(
                "%-10d %-15d %-15s %-10.2f",
                id, accountNumber, name, balance
        );
    }



}
