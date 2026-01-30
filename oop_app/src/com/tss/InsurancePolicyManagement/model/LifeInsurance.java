package com.tss.InsurancePolicyManagement.model;
import com.insurance.model.InsurancePolicy;

public class LifeInsurance extends InsurancePolicy {

    private boolean isMatured;
    public LifeInsurance(int policyNumber, String policyHolderName,
                         double sumAssured, int duration, boolean isMatured) {
        super(policyNumber, policyHolderName, sumAssured, duration);
        this.isMatured = isMatured;
    }

    @Override
    public double calculatePremium() {
        return sumAssured * 0.05 * duration;
    }
//
//    @Override
//    public boolean applyClaim(double claimAmount) {
//        System.out.println("Claim allowed only after policy maturity.");
//        return false;
//    }

    @Override
    public boolean applyClaim(double claimAmount) {

        if (!isMatured) {
            System.out.println("Claim denied. Policy has not matured yet.");
            return false;
        }

        if (claimAmount <= 0) {
            System.out.println("Invalid claim amount.");
            return false;
        }

        if (claimAmount <= remainingSumAssured) {
            remainingSumAssured -= claimAmount;
            System.out.println("Life insurance claim approved for amount: " + claimAmount);
            return true;
        } else {
            System.out.println("Claim denied. Insufficient sum assured.");
            return false;
        }
    }
    public void maturePolicy() {
        this.isMatured = true;
        System.out.println("Policy has matured. Claims are now allowed.");
    }


}
