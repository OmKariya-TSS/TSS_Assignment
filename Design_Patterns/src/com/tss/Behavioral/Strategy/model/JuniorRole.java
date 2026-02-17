package com.tss.Behavioral.Strategy.model;

public class JuniorRole implements Role {

    @Override
    public void applyRole() {
        System.out.print("Works under supervision. ");
    }
}
