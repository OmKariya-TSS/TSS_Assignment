package com.tss.model;

public class Account {
    int id;
    int accountNumber;
    String name;
    double balance;
    AccountType type;
    public static int count=0;
//    public Account(int id,int accountNumber,String name,double balance) {
//        this.id = id;
//        count++;
//    }


    public Account(int id, AccountType type) {
        this.id = id;
        this.type = type;
        count++;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


}
