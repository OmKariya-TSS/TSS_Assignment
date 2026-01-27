package com.tss.AccountManagement.v1.model;

import java.util.Random;

public class AccountInheritence {
    private static int idCounter = 1;
    private int id;
    private int accountNumber;
    private String name;
    protected double balance;
    private Random random = new Random();
    private int generateAccId() {
        return random.nextInt(9000) + 1000;
    }
    public AccountInheritence(String name, double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
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
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            System.out.println("Name cannot be empty");
            return;
        }
        this.name = name;
    }
    public void setBalance(double balance) {
        if (balance < 0) {
            System.out.println("Balance cannot be negative");
            return;
        }
        this.balance = balance;
    }
    public double deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Please enter a valid deposit amount");
            return 0;
        }
        balance += amount;
        return amount;
    }
    public double withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Please enter a valid withdrawal amount");
            return 0;
        }
        if (amount > balance) {
            System.out.println("Insufficient balance");
            return 0;
        }
        balance -= amount;
        return amount;
    }
}
