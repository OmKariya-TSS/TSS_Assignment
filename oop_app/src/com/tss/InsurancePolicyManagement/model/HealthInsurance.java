package com.tss.InsurancePolicyManagement.model;
import com.insurance.model.InsurancePolicy;

public class HealthInsurance extends InsurancePolicy {
    public HealthInsurance(int policyNumber, String policyHolderName, double sumAssured, int duration) {
        super(policyNumber, policyHolderName, sumAssured, duration);
    }

    @Override
    public double calculatePremium() {
        return sumAssured * 0.07 * duration;
    }

    @Override
    public boolean applyClaim() {
        return true;
    }
}
