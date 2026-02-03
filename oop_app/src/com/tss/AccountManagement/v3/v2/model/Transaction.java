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
    public static void printHeader() {
        System.out.println("-------------------------------------------------------------------------------");
        System.out.printf(
                "%-5s %-10s %-10s %-12s %-12s %-20s\n",
                "ID", "Type", "Amount", "From Acc", "To Acc", "Date & Time"
        );
        System.out.println("-------------------------------------------------------------------------------");
    }
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

    @Override
    public String toString() {
        return String.format(
                "%-5d %-10s %-10.2f %-12d %-12s %-20s",
                transactionId,
                type,
                amount,
                fromAccountId,
                (toAccountId != null ? toAccountId : "N/A"),
                timestamp
        );
    }
}
