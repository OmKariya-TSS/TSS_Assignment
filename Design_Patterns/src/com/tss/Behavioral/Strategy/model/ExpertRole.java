package com.tss.Behavioral.Strategy.model;

public class ExpertRole implements Role {

    @Override
    public void applyRole() {
        System.out.print("Makes architectural and strategic decisions. ");
    }
}
