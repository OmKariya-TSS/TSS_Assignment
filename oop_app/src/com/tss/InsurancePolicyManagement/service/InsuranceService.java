package com.tss.InsurancePolicyManagement.service;
import com.insurance.model.InsurancePolicy;
import com.tss.InsurancePolicyManagement.model.HealthInsurance;
import com.tss.InsurancePolicyManagement.model.LifeInsurance;
import com.tss.InsurancePolicyManagement.model.VehicleInsurance;


public class InsuranceService {
    private InsurancePolicy[] policies = new InsurancePolicy[50];
    private int policyCount = 0;
    public void addPolicy(String policyType, int policyNumber, String holderName, double sumAssured, int duration, boolean isActive) {
        if (policyCount == policies.length) {
            System.out.println("Policy limit reached!");
            return;
        }
        InsurancePolicy policy = null;
        switch (policyType.toLowerCase()) {
            case "life":
                policy = new LifeInsurance(policyNumber, holderName, sumAssured, duration);
                break;
            case "health":
                policy = new HealthInsurance(policyNumber, holderName, sumAssured, duration);
                break;
            case "vehicle":
                policy = new VehicleInsurance(policyNumber, holderName, sumAssured, duration, isActive);
                break;
            default:
                System.out.println("Invalid policy type!");
                return;
        }
        policies[policyCount++] = policy;
        System.out.println(policyType + " policy created successfully with policy number: " + policy.policyNumber);
    }

    public InsurancePolicy findPolicy(int policyNumber) {
        for (int i = 0; i < policyCount; i++) {
            if (policies[i].policyNumber == policyNumber) {
                return policies[i];
            }
        }
        return null;
    }

    public void showAllPolicies() {
        if (policyCount == 0) {
            System.out.println("No policies found.");
            return;
        }
        System.out.println("==== All Policies ====");
        for (int i = 0; i < policyCount; i++) {
            policies[i].displayPolicyDetails();
            System.out.println("Premium: " + policies[i].calculatePremium());
            System.out.println("------------------------");
        }
    }
}
