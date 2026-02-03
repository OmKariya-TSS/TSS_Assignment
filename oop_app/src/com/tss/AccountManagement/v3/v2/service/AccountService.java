package com.tss.AccountManagement.v3.v2.service;

import com.tss.AccountManagement.v3.v2.Exception.AccountNotFoundException;
import com.tss.AccountManagement.v3.v2.model.*;

import java.util.ArrayList;
import java.util.List;

public class AccountService {

    private List<Account> accounts = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();

    public void createAccount(String accountType, String name, double balance) {
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
                    System.out.println("Invalid account type.");
                    return;
            }
            accounts.add(account);
            System.out.println(accountType + " account created with ID: " + account.getId());

        } catch (Exception e) {
            System.out.println("Error creating account: " + e.getMessage());
        }
    }

    public Account findAccountById(int id) {
        try {
            for (Account account : accounts) {
                if (account.getId() == id) {
                    return account;
                }
            }
            throw new AccountNotFoundException(id);

        } catch (AccountNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }


    public void deposit(int id, double amount) {
        try {
            Account account = findAccountById(id);
            account.deposit(amount);

            transactions.add(
                    new Transaction(TransactionType.DEPOSIT, amount, id, null)
            );

            System.out.println("Deposit successful.");

        } catch (Exception e) {
            System.out.println("Deposit failed: " + e.getMessage());
        }
    }

    public void withdraw(int id, double amount) {
        try {
            Account account = findAccountById(id);
            account.withdraw(amount);

            transactions.add(
                    new Transaction(TransactionType.WITHDRAW, amount, id, null)
            );

            System.out.println("Withdrawal successful.");

        } catch (Exception e) {
            System.out.println("Withdrawal failed: " + e.getMessage());
        }
    }

    public void transfer(int fromId, int toId, double amount) {
        if (fromId == toId) {
            System.out.println("Cannot transfer to same account.");
            return;
        }

        try {
            Account sender = findAccountById(fromId);
            Account receiver = findAccountById(toId);

            sender.withdraw(amount);
            receiver.deposit(amount);

            transactions.add(
                    new Transaction(TransactionType.TRANSFER, amount, fromId, toId)
            );

            System.out.println("Transfer successful.");

        } catch (Exception e) {
            System.out.println("Transfer failed: " + e.getMessage());
        }
    }

    public void showAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }
        for (Account account : accounts) {
            account.display();
        }
    }

    public void showAllTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }
        for (Transaction t : transactions) {
            t.display();
        }
    }

    public void showTransactionsByAccount(int accountId) {
        boolean found = false;
        for (Transaction t : transactions) {
            if (t.getFromAccountId() == accountId ||
                    (t.getToAccountId() != null && t.getToAccountId() == accountId)) {
                t.display();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No transactions for account ID " + accountId);
        }
    }
    public void deleteAccount(int id){
        Account account = findAccountById(id);
        accounts.remove(account);

        System.out.println("Account with ID " + id + " deleted successfully.");

    }

}
