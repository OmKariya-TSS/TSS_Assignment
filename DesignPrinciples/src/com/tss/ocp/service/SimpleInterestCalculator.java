package com.tss.ocp.service;


import com.tss.ocp.model.FixedDeposit;

public class SimpleInterestCalculator {

    public double calculate(FixedDeposit fd) {
        double rate = fd.getFestivalType().rate();
        return fd.getPrincipalAmount() * rate * fd.getDuration() / 100;
    }
}
