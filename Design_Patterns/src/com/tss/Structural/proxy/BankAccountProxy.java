package com.tss.Structural.proxy;

class BankAccountProxy implements BankAccount {

    private RealBankAccount realAccount;
    private String userRole;

    public BankAccountProxy(double balance, String userRole) {
        this.realAccount = new RealBankAccount(balance);
        this.userRole = userRole;
    }

    @Override
    public void withdraw(double amount) {
        if ("OWNER".equalsIgnoreCase(userRole)) {
            realAccount.withdraw(amount);
        } else {
            System.out.println("Access Denied: Only owner can withdraw.");
        }
    }

    @Override
    public void getBalance() {
        realAccount.getBalance();
    }
}

