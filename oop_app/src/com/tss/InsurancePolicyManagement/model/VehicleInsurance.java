package com.tss.InsurancePolicyManagement.model;

import com.insurance.model.InsurancePolicy;

public class VehicleInsurance extends InsurancePolicy {

    private boolean isActive;
    public VehicleInsurance(int policyNumber, String policyHolderName, double sumAssured, int duration, boolean isActive) {
        super(policyNumber, policyHolderName, sumAssured, duration);
        this.isActive = isActive;
    }
    @Override
    public double calculatePremium() {
        return sumAssured * 0.03 * duration;
    }

    @Override
    public boolean applyClaim(double claimAmount) {
        if (!isActive) {
            System.out.println("Claim denied. Policy is not active.");
            return false;
        }

        if (claimAmount <= remainingSumAssured) {
            remainingSumAssured -= claimAmount;
            System.out.println("Vehicle claim approved for amount: " + claimAmount);
            return true;
        } else {
            System.out.println("Claim denied. Insufficient sum assured.");
            return false;
        }
    }
}
