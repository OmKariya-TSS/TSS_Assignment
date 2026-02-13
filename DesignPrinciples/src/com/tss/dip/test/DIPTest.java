package com.tss.dip.test;


import java.util.Scanner;

import com.tss.dip.model.*;

public class DIPTest {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Choose DB for Logging");
        System.out.println("1. MySQL");
        System.out.println("2. Oracle");
        System.out.println("3. MongoDB");
        System.out.println("4. PostgreSQL");

        int choice = sc.nextInt();

        Logger logger = null;

        switch (choice) {
            case 1 -> logger = new MySQLLogger();
            case 2 -> logger = new OracleLogger();
            case 3 -> logger = new MongoDB();
            case 4 -> logger = new PostgresLogger();
            default -> {
                System.out.println("Invalid choice");
                System.exit(0);
            }
        }

        Invoice invoice = new Invoice(101, "Laptop", 50000);

        TaxCalculator calculator = new TaxCalculator(logger);

        double tax = calculator.calculateTax(invoice);

        System.out.println("Final Tax = " + tax);

        sc.close();
    }
}
