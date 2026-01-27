package com.tss.AccountManagement.v2.test;
import com.tss.AccountManagement.v2.model.Account;
import com.tss.AccountManagement.v2.service.AccountService;
import java.util.Scanner;

public class AccountMain {

    private static Scanner scanner = new Scanner(System.in);
    private static AccountService accountService = new AccountService();

    public static void main(String[] args) {
        System.out.println("Welcome to Account Management");
        menu();
    }
    public static void menu() {
        System.out.println("1: Create Account");
        System.out.println("2: Deposit");
        System.out.println("3: Withdraw");
        System.out.println("4: Show Balance");
        System.out.println("5: Transfer");
        System.out.println("6: Show an Account");
        System.out.println("7: Show All Accounts");
        System.out.println("8: Exit");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1 :
                createAccountMenu();
                break;
            case 2 :
                depositMenu();
                break;
            case 3 :
                withdrawMenu();
                break;
            case 4 :
                showBalanceMenu();
                break;
            case 5 :
                transferMenu();
                break;
            case 6 :
                System.out.print("Enter account ID: ");
                int accountId = scanner.nextInt();
                accountService.showAccount(accountId);
                menu();
                break;
            case 7 :
                accountService.showAllAccounts();
                menu();
                break;
            case 8 :
                System.out.println("Exiting...");
                break;
            default :
                System.out.println("Enter a valid choice");
                menu();
        }
    }
    public static void createAccountMenu() {
        System.out.println("Select account type:");
        System.out.println("1: Savings");
        System.out.println("2: Current");
        int type = scanner.nextInt();
        System.out.print("Enter name: ");
        String name = scanner.next();
        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();
        if (type == 1) {
            accountService.createAccount("savings",name,balance);
        } else if (type == 2) {
            if (balance >= 500) {
                accountService.createAccount("current",name,balance);
            } else {
                System.out.println("Cannot create Current Account. Minimum balance: 500");
            }
        } else {
            System.out.println("Invalid account type");
        }
        menu();
    }
    public static void depositMenu() {
        System.out.print("Enter account ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();
        Account acc = accountService.findAccountById(id);
        if (acc != null) {
            acc.deposit(amount);
        } else {
            System.out.println("Account not found");
        }
        menu();
    }
    public static void withdrawMenu() {
        System.out.print("Enter account ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();
        Account acc = accountService.findAccountById(id);
        if (acc != null) {
            acc.withdraw(amount);
        } else {
            System.out.println("Account not found");
        }
        menu();
    }
    public static void showBalanceMenu() {
        System.out.print("Enter account ID: ");
        int id = scanner.nextInt();
        accountService.showBalance(id);
        menu();
    }
    public static void transferMenu() {
        System.out.print("Enter sender account ID: ");
        int fromId = scanner.nextInt();
        System.out.print("Enter receiver account ID: ");
        int toId = scanner.nextInt();
        System.out.print("Enter transfer amount: ");
        double amount = scanner.nextDouble();
        accountService.transfer(fromId, toId, amount);
        menu();
    }
}
