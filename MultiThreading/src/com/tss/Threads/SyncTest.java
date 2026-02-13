package com.tss.Threads;

class Bank {
    int balance = 1000;

    synchronized void withdraw(int amount) {
        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " withdrawing");
            balance -= amount;
            System.out.println("Balance left: " + balance);
        } else {
            System.out.println("Insufficient balance");
        }
    }
}

class Customer extends Thread {
    Bank b;

    Customer(Bank b) {
        this.b = b;
    }

    public void run() {
        b.withdraw(700);
    }
}

public class SyncTest {
    public static void main(String[] args) {
        Bank b = new Bank();

        new Customer(b).start();
        new Customer(b).start();
    }
}
