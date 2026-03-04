package com.tss.FoodAppV4.ui;


import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHelper {

    private final Scanner scanner;

    public InputHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    public int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int val = scanner.nextInt();
                scanner.nextLine();
                return val;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("  ❌ Invalid input! Please enter a number.");
            }
        }
    }

    public double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double val = scanner.nextDouble();
                scanner.nextLine();
                return val;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("  ❌ Invalid input! Please enter a number.");
            }
        }
    }

    public Scanner getScanner() {
        return scanner;
    }
}