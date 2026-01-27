package com.tss.AccountManagement.v2.service;

import com.tss.AccountManagement.v2.model.Account;
import com.tss.AccountManagement.v2.model.CurrentAccount;
import com.tss.AccountManagement.v2.model.SavingsAccount;

public class AccountService {
    private Account[] accounts = new Account[50];
    private int accountCount = 0;

    public void createAccount(String accountType, String name, double balance) {
        if (accountCount == accounts.length) {
            System.out.println("Account limit reached. Cannot create more accounts.");
            return;
        }

        if (name == null || name.isEmpty()) {
            System.out.println("Account name cannot be empty.");
            return;
        }

        if (balance < 0) {
            System.out.println("Initial balance cannot be negative.");
            return;
        }

        Account account = null;
        switch (accountType.toLowerCase()) {
            case "savings" -> account = new SavingsAccount(name, balance);
            case "current" -> {
                if (balance >= CurrentAccount.MIN_BALANCE) {
                    account = new CurrentAccount(name, balance);
                } else {
                    System.out.println("Cannot create Current Account. Minimum balance required: " + CurrentAccount.MIN_BALANCE);
                    return;
                }
            }
            default -> {
                System.out.println("Invalid account type. Choose 'savings' or 'current'.");
                return;
            }
        }

        accounts[accountCount++] = account;
        System.out.println(accountType + " account created successfully with ID: " + account.getId());
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
        if (amount <= 0) {
            System.out.println("Transfer amount must be greater than zero.");
            return;
        }
        if (fromId == toId) {
            System.out.println("Cannot transfer to the same account.");
            return;
        }

        Account sender = findAccountById(fromId);
        Account receiver = findAccountById(toId);

        if (sender == null || receiver == null) {
            System.out.println("One or both accounts not found. Transfer aborted.");
            return;
        }

        double withdrawn = sender.withdraw(amount);
        if (withdrawn == 0) {
            System.out.println("Transfer failed due to insufficient funds or minimum balance requirement.");
            return;
        }

        receiver.deposit(withdrawn);
        System.out.println("Transfer of " + amount + " from Account " + fromId + " to Account " + toId + " successful.");
    }
    public void showBalance(int id) {
        Account account = findAccountById(id);
        if (account == null) return;

        account.display();
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
