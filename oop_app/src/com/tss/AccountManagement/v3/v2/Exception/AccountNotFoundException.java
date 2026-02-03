package com.tss.AccountManagement.v3.v2.Exception;

public class AccountNotFoundException extends Exception {
    private final int id;

    public AccountNotFoundException(int id) {
        super("Account with ID " + id + " not found.");
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
