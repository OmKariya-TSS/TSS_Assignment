package com.tss.ocp.model;

public class FixedDeposit {

    private int accountNo;
    private String name;
    private double principalAmount;
    private int duration;
    private FestivalType festivalType;

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(double principalAmount) {
        this.principalAmount = principalAmount;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public FestivalType getFestivalType() {
        return festivalType;
    }

    public void setFestivalType(FestivalType festivalType) {
        this.festivalType = festivalType;
    }

    public FixedDeposit(int accountNo, String name,
                        double principalAmount,
                        int duration,
                        FestivalType festivalType) {

        this.accountNo = accountNo;
        this.name = name;
        this.principalAmount = principalAmount;
        this.duration = duration;
        this.festivalType = festivalType;
    }

    public double calculateInterest() {
        return principalAmount * festivalType.rate() * duration / 100;
    }
}
