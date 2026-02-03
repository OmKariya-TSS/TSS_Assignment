package com.tss.AccountManagement.v3.v2.test;

import com.tss.AccountManagement.v3.v2.Exception.AccountNotFoundException;
import com.tss.AccountManagement.v3.v2.Exception.InsufficientBalanceException;
import com.tss.AccountManagement.v3.v2.Exception.InvalidAmountException;
import com.tss.AccountManagement.v3.v2.Exception.MinimumBalanceException;
import com.tss.AccountManagement.v3.v2.model.Account;
import com.tss.AccountManagement.v3.v2.service.AccountService;

import java.util.Scanner;

public class AccountMain {

    private static Scanner scanner = new Scanner(System.in);
    private static AccountService accountService = new AccountService();

    public static void main(String[] args) throws AccountNotFoundException {
        System.out.println("Welcome to Account Management");
        menu();
    }

    public static void menu() throws AccountNotFoundException {
        System.out.println("\n1: Create Account");
        System.out.println("2: Deposit");
        System.out.println("3: Withdraw");
        System.out.println("4: Show Balance");
        System.out.println("5: Transfer");
        System.out.println("6: Show an Account");
        System.out.println("7: Show All Accounts");
        System.out.println("8: Delete account by id");
        System.out.println("9: Show all transactions");
        System.out.println("10: Show transactions by account");
        System.out.println("11: Exit");
        System.out.print("Enter choice: ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> createAccountMenu();
            case 2 -> depositMenu();
            case 3 -> withdrawMenu();
            case 4 -> showBalanceMenu();
            case 5 -> transferMenu();
            case 6 -> {
                System.out.print("Enter account ID: ");
                int accountId = scanner.nextInt();
                accountService.findAccountById(accountId);
                menu();
            }
            case 7 -> {
                accountService.showAllAccounts();
                menu();
            }
            case 8 -> deleteAccount();
            case 9 -> {
                accountService.showAllTransactions();
                menu();
            }
            case 10 -> {
                System.out.print("Enter account ID: ");
                int id = scanner.nextInt();
                accountService.showTransactionsByAccount(id);
                menu();
            }
            case 11 -> System.out.println("Exiting...");
            default -> {
                System.out.println("Enter a valid choice");
                menu();
            }
        }
    }

    public static void createAccountMenu() throws AccountNotFoundException {
        System.out.println("Select account type:");
        System.out.println("1: Savings");
        System.out.println("2: Current");
        int type=0;
        while(type<1 || type>2){
            System.out.println("enter type: ");
            type = scanner.nextInt();
        }
        System.out.print("Enter name: ");
        String name = scanner.next();
        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();

        if (type == 1) {
            accountService.createAccount("savings", name, balance);
        } else if (type == 2) {
            accountService.createAccount("current", name, balance);
        } else {
            System.out.println("Invalid account type");
        }
        menu();
    }

    public static void depositMenu() throws AccountNotFoundException {
        try {
            System.out.print("Enter account ID: ");
            int id = scanner.nextInt();
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            accountService.deposit(id, amount);
        } catch (InvalidAmountException e) {
            System.out.println("Invalid Amount: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred.");
        }
        menu();
    }


    public static void withdrawMenu() throws AccountNotFoundException {
        try {
            System.out.print("Enter account ID: ");
            int id = scanner.nextInt();
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            accountService.withdraw(id, amount);
        }
        catch (InvalidAmountException | MinimumBalanceException e) {
            System.out.println("Transaction Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
        menu();
    }


    public static void showBalanceMenu() throws AccountNotFoundException {
        try {
            System.out.print("Enter account ID: ");
            int id = scanner.nextInt();
            Account acc = accountService.findAccountById(id);
            double balance = acc.getBalance();
            System.out.println("Balance for Account id :"+id +" is "+balance + " Rupees");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        menu();
    }


    public static void transferMenu() throws AccountNotFoundException {
        System.out.print("Enter sender account ID: ");
        int fromId = scanner.nextInt();
        Account sender =  accountService.findAccountById(fromId);
        System.out.print("Enter receiver account ID: ");
        int toId = scanner.nextInt();
        Account receiver = accountService.findAccountById(toId);
        System.out.print("Enter transfer amount: ");
        double amount = scanner.nextDouble();

        accountService.transfer(fromId, toId, amount);
        menu();
    }
    public static void deleteAccount() throws AccountNotFoundException {
        System.out.println("Enter account id to delete :");
        int id = scanner.nextInt();
        accountService.deleteAccount(id);
        menu();
    }
}
