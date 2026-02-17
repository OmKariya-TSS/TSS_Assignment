package com.tss.Behavioral.Observer.test;

import com.tss.Behavioral.Observer.model.Account;
import com.tss.Behavioral.Observer.notifiers.Email;
import com.tss.Behavioral.Observer.notifiers.SMS;

public class AccountTest {
    public static void main(String[] args) {
             Account account = new Account(1, "Om", 1000);
                account.addNotifier(new Email());
                account.addNotifier(new SMS());
                account.deposit(500);
                account.withdraw(200);
    }
}
