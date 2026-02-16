package com.tss.Structural.composite.leaf;

import com.tss.Structural.composite.component.HomeComponent;

public class Appliance implements HomeComponent {
    private String name;

    public Appliance(String name) {
        this.name = name;
    }

    @Override
    public void turnOn() {
        System.out.println(name + " turned ON");
    }

    @Override
    public void turnOff() {
        System.out.println(name + " turned OFF");
    }
}
