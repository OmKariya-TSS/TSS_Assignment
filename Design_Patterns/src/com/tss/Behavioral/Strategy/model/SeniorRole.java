package com.tss.Behavioral.Strategy.model;

public class SeniorRole implements Role {

    @Override
    public void applyRole() {
        System.out.print("Works independently and mentors juniors. ");
    }
}
