package com.tss.Behavioral.Strategy.model;

public class DesignerResponsibility implements Responsibility {

    @Override
    public void performResponsibility() {
        System.out.println("Responsible for UI/UX design.");
    }
}
