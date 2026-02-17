package com.tss.Behavioral.Observer.model;

import com.tss.Behavioral.Observer.notifiers.INotifier;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private int id;
    private String name;
    private double balance;
    private List<INotifier> notifiers = new ArrayList<>();

    public Account(int id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public void addNotifier(INotifier notifier) {
        notifiers.add(notifier);
    }

    public void removeNotifier(INotifier notifier) {
        notifiers.remove(notifier);
    }

    private void notifyObservers(String message) {
        for (INotifier notifier : notifiers) {
            notifier.sendAlert(message);
        }
    }

    public double deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Please enter a valid deposit amount");
            return 0;
        }

        balance += amount;

        notifyObservers("Deposit successful. Current balance: " + balance);
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

        notifyObservers("Withdrawal successful. Current balance: " + balance);
        return amount;
    }
}
