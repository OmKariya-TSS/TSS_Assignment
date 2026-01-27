package com.tss.InsurancePolicyManagement.test;

import com.tss.InsurancePolicyManagement.service.InsuranceService;
import    com.insurance.model.InsurancePolicy;
import java.util.Scanner;

public class InsuranceTest {

    private static final Scanner scanner = new Scanner(System.in);
    private static final InsuranceService insuranceService = new InsuranceService();

    public static void main(String[] args) {
        System.out.println("Welcome to Insurance Policy Management System");
        menu();
    }
    public static void menu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1: Create Policy");
        System.out.println("2: Calculate Premium");
        System.out.println("3: Apply Claim");
        System.out.println("4: Show Policy Details");
        System.out.println("5: Show All Policies");
        System.out.println("6: Exit");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 :
                createPolicyMenu();
                break;
            case 2 :
                calculatePremiumMenu();
                break;
            case 3 :
                applyClaimMenu();
                break;
            case 4 :
                showPolicyMenu();
                break;
            case 5 :
                insuranceService.showAllPolicies();
                menu();
                break;
            case 6 :
                System.out.println("Exiting... Goodbye!");
                break;
            default :
                System.out.println("Invalid choice, try again.");
                menu();
                break;

        }
    }
    public static void createPolicyMenu() {
        System.out.println("Select Policy Type:");
        System.out.println("1: Life Insurance");
        System.out.println("2: Health Insurance");
        System.out.println("3: Vehicle Insurance");
        System.out.print("Enter choice: ");
        int typeChoice = scanner.nextInt();
        String type;
        switch (typeChoice) {
            case 1 -> type = "life";
            case 2 -> type = "health";
            case 3 -> type = "vehicle";
            default -> {
                System.out.println("Invalid choice! Returning to main menu.");
                menu();
                return;
            }
        }
        System.out.print("Enter Policy Number: ");
        int policyNumber = scanner.nextInt();
        System.out.print("Enter Policy Holder Name: ");
        String holderName = scanner.next();
        System.out.print("Enter Sum Assured: ");
        double sumAssured = scanner.nextDouble();
        System.out.print("Enter Policy Duration (years): ");
        int duration = scanner.nextInt();
        boolean isActive = true;
        if (type.equals("vehicle")) {
            System.out.print("Is the policy active? (true/false): ");
            isActive = scanner.nextBoolean();
        }
        insuranceService.addPolicy(type, policyNumber, holderName, sumAssured, duration, isActive);
        menu();
    }

    public static void calculatePremiumMenu() {
        System.out.print("Enter Policy Number: ");
        int policyNumber = scanner.nextInt();
        com.insurance.model.InsurancePolicy policy = insuranceService.findPolicy(policyNumber);
        if (policy == null) {
            System.out.println("Policy not found.");
        } else {
            double premium = policy.calculatePremium();
            System.out.println("Premium for policy " + policyNumber + " is: " + premium);
        }

        menu();
    }

    public static void applyClaimMenu() {
        System.out.print("Enter Policy Number: ");
        int policyNumber = scanner.nextInt();
        InsurancePolicy policy = insuranceService.findPolicy(policyNumber);
        if (policy == null) {
            System.out.println("Policy not found.");
        } else {
            boolean success = policy.applyClaim();
            if (success) {
                System.out.println("Claim applied successfully for policy " + policyNumber);
            } else {
                System.out.println("Claim denied for policy " + policyNumber);
            }
        }
        menu();
    }
    public static void showPolicyMenu() {
        System.out.print("Enter Policy Number: ");
        int policyNumber = scanner.nextInt();
        InsurancePolicy policy = insuranceService.findPolicy(policyNumber);
        if (policy == null) {
            System.out.println("Policy not found.");
        } else {
            policy.displayPolicyDetails();
            System.out.println("Premium: " + policy.calculatePremium());
        }
        menu();
    }
}
