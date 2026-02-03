package com.tss.AccountManagement.v2.service;

import com.tss.AccountManagement.v2.Exception.*;
import com.tss.AccountManagement.v2.model.*;
import com.tss.Exception.*;

public class AccountService {

    private Account[] accounts = new Account[50];
    private int accountCount = 0;

    public void createAccount(String accountType, String name, double balance) {

        if (accountCount == accounts.length) {
            System.out.println("Account limit reached. Cannot create more accounts.");
            return;
        }

        try {
            Account account;

            switch (accountType.toLowerCase()) {

                case "savings":
                    account = new SavingsAccount(name, balance);
                    break;

                case "current":
                    account = new CurrentAccount(name, balance);
                    break;

                default:
                    System.out.println("Invalid account type. Choose 'savings' or 'current'.");
                    return;
            }

            accounts[accountCount++] = account;
            System.out.println(
                    accountType + " account created successfully with ID: " + account.getId()
            );

        } catch (RuntimeException e) {

            System.out.println(e.getMessage());
        }
    }

    public Account findAccountById(int id) {

        if (id <= 0) {
            System.out.println("Invalid account ID.");
            return null;
        }

        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getId() == id) {
                return accounts[i];
            }
        }

        System.out.println("Account with ID " + id + " not found.");
        return null;
    }

    public void transfer(int fromId, int toId, double amount) {
        if (fromId == toId) {
            System.out.println("Cannot transfer to the same account.");
            return;
        }

        Account sender = findAccountById(fromId);
        Account receiver = findAccountById(toId);

        if (sender == null || receiver == null) {
            return;
        }

        try {
            sender.withdraw(amount);
            receiver.deposit(amount);

            System.out.println("Transfer of " + amount + " from ID " + fromId +
                    " to ID " + toId + " successful.");

        } catch (Exception e) {
            System.out.println("Transfer failed: " + e.getMessage());
        }
    }


    public void showBalance(int id) {
        Account account = findAccountById(id);
        if (account != null) {
            account.display();
        }
    }

    public void showAccount(int id) {
        showBalance(id);
    }

    public void showAllAccounts() {

        if (accountCount == 0) {
            System.out.println("No accounts available.");
            return;
        }

        System.out.println("==== All Accounts ====");
        for (int i = 0; i < accountCount; i++) {
            accounts[i].display();
        }
    }
}
