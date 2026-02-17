package com.tss.Behavioral.Strategy.model;

public class DeveloperResponsibility implements Responsibility {

    @Override
    public void performResponsibility() {
        System.out.println("Responsible for writing and maintaining code.");
    }
}
