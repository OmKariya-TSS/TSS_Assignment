package com.tss.AccountManagement.v3.v2.model;


import java.time.LocalDateTime;

public class Transaction {

    private static int transactionCounter = 1;

    private int transactionId;
    private TransactionType type;
    private double amount;
    private int fromAccountId;
    private Integer toAccountId;
    private LocalDateTime timestamp;

    public Transaction(TransactionType type, double amount,
                       int fromAccountId, Integer toAccountId) {

        this.transactionId = transactionCounter++;
        this.type = type;
        this.amount = amount;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.timestamp = LocalDateTime.now();
    }

    public int getTransactionId() {
        return transactionId;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public int getFromAccountId() {
        return fromAccountId;
    }

    public Integer getToAccountId() {
        return toAccountId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void display() {
        System.out.println("---- Transaction ----");
        System.out.println("Transaction ID     : " + transactionId);
        System.out.println("Type       : " + type);
        System.out.println("Amount     : " + amount);
        System.out.println("From Acc   : " + fromAccountId);
        System.out.println("To Acc     : " + (toAccountId != null ? toAccountId : "N/A"));
        System.out.println("Date/Time  : " + timestamp);
        System.out.println("---------------------");
    }
}
