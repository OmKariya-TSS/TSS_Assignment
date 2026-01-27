package com.tss.AccountManagement.v1.test;
import com.tss.AccountManagement.v1.model.SavingsAccount;
import com.tss.AccountManagement.v1.model.CurrentAccount;
import java.util.Scanner;
import static com.tss.AccountManagement.v1.model.CurrentAccount.MIN_BALANCE;
public class AccountMain{
    public static void main(String[] args) {
        System.out.println("welcome to account management");
        menu();
    }
    public static void menu() {
        CurrentAccount curr = null;
        SavingsAccount sav = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("which account you want to create");
        System.out.println("1: Savings");
        System.out.println("2: Current");
        int type = scanner.nextInt();
        switch (type) {
            case 1:
                System.out.println("enter name:");
                String name = scanner.next();
                System.out.println("enter balance  :");
                double balance = scanner.nextDouble();
                sav = new SavingsAccount(name, balance);
                SavingMenu(sav);
                break;
            case 2:
                System.out.println("enter name:");
                name = scanner.next();
                System.out.println("enter balance  :");
                balance = scanner.nextDouble();
                if (balance > MIN_BALANCE) {
                    curr = new CurrentAccount(name, balance);
                    CurrentMenu(curr);
                }else {
                    System.out.println("cant create account min balance required 500");
                }
                menu();
                break;
            default:
                System.out.println("enter valid number");
                menu();
                break;
        }
    }
    public static void CurrentMenu (CurrentAccount currentAccount) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1: Deposit");
        System.out.println("2 : withdraw");
        System.out.println("3: show balance");
        System.out.println("4 : exit");
        System.out.println("enter from 1 to 5 :");
        int n = scanner.nextInt();
        switch (n) {
            case 1:
                System.out.println("enter deposit amount");
                double amount = scanner.nextDouble();
                currentAccount.deposit(amount);
                CurrentMenu(currentAccount);
                break;
            case 2:
                System.out.println("enter amount tov withdraw");
                amount = scanner.nextDouble();
                currentAccount.withdraw(amount);
                CurrentMenu(currentAccount);
                break;
            case 3:
                System.out.println("balance is:");
                System.out.println(currentAccount.getBalance());
                CurrentMenu(currentAccount);
                break;
            case 4:
                menu();
                break;
            default:
                System.out.println("enter valid number");
                CurrentMenu(currentAccount);
                break;

        }
    }
    public static void SavingMenu (SavingsAccount sav) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1: Deposit");
        System.out.println("2 : withdraw");
        System.out.println("3: show balance");
        System.out.println("4 : exit");
        System.out.println("enter from 1 to 4 :");
        int n = scanner.nextInt();
        switch (n) {
            case 1:
                System.out.println("enter deposit amount");
                double amount = scanner.nextDouble();
                sav.deposit(amount);
                SavingMenu(sav);
                break;
            case 2:
                System.out.println("enter amount to withdraw");
                amount = scanner.nextDouble();
                sav.withdraw(amount);
                SavingMenu(sav);
                break;
            case 3:
                System.out.println("balance is:");
                System.out.println(sav.getBalance());
                SavingMenu(sav);
                break;
            case 4:
                menu();
                break;
            default:
                System.out.println("enter valid ");
                SavingMenu(sav);
        }
    }

}