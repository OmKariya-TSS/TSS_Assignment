package com.tss.streams.model;

public class Account {
    int accountNumer;
    String name;
    double balance;
    static int idCounter=1;
    public Account(String name, double balance) {
        this.accountNumer = idCounter++;
        this.name = name;
        this.balance = balance;
    }

    public int getAccountNumer() {
        return accountNumer;
    }

    public void setAccountNumer(int accountNumer) {
        this.accountNumer = accountNumer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Account.idCounter = idCounter;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumer=" + accountNumer +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
