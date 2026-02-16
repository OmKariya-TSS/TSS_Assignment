package com.tss.Structural.proxy;

public class Main {
    public static void main(String[] args) {

        BankAccount account1 = new BankAccountProxy(1000, "OWNER");
        account1.withdraw(200);
        account1.getBalance();

        BankAccount account2 = new BankAccountProxy(1000, "GUEST");
        account2.withdraw(200);
        account2.getBalance();
    }
}
