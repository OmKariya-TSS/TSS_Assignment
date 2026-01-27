package com.tss.test;

import com.tss.model.Account;
import com.tss.model.AccountType;
import java.util.Scanner;

public class AccountTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select Account Type: 1. SAVINGS, 2. CURRENT, 3. SALARY");
        int n = scanner.nextInt();
        AccountType selectedType = switch (n) {
            case 1 -> AccountType.SAVINGS;
            case 2 -> AccountType.CURRENT;
            case 3 -> AccountType.SALARY;
            default -> {
                System.out.println("invalid");
                yield AccountType.SAVINGS;
            }
        };
        Account ac1 = new Account(1, selectedType);
        System.out.println("Account created with type: " + selectedType);
        System.out.println("Total accounts: " + Account.count);
    }
}
