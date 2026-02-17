package com.tss.Behavioral.Strategy.model;

public class HRResponsibility implements Responsibility {

    @Override
    public void performResponsibility() {
        System.out.println("Responsible for recruitment and employee relations.");
    }
}