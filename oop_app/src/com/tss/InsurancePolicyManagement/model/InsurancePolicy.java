package com.insurance.model;

public abstract class InsurancePolicy {
    public int policyNumber;
    protected String policyHolderName;
    protected double sumAssured;
    protected int duration;
    public InsurancePolicy(int policyNumber, String policyHolderName, double sumAssured, int duration) {
        this.policyNumber = policyNumber;
        this.policyHolderName = policyHolderName;
        this.sumAssured = sumAssured;
        this.duration = duration;
    }
    public abstract double calculatePremium();
    public abstract boolean applyClaim();
    public void displayPolicyDetails() {
        System.out.println("Policy Number: " + policyNumber);
        System.out.println("Policy Holder: " + policyHolderName);
        System.out.println("Sum Assured: " + sumAssured);
        System.out.println("Duration: " + duration + " years");
    }
}
