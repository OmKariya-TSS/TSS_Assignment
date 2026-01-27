package com.tss.InsurancePolicyManagement.model;
import com.insurance.model.InsurancePolicy;

public class LifeInsurance extends InsurancePolicy {

    public LifeInsurance(int policyNumber, String policyHolderName, double sumAssured, int duration) {
        super(policyNumber, policyHolderName, sumAssured, duration);
    }

    @Override
    public double calculatePremium() {
        return sumAssured * 0.05 * duration;
    }

    @Override
    public boolean applyClaim() {
        System.out.println("Claim allowed only after policy maturity.");
        return false;
    }
}
