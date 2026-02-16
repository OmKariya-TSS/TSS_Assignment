package com.tss.Structural.composite.compositeComponents;

import com.tss.Structural.composite.component.HomeComponent;

import java.util.ArrayList;
import java.util.List;

public class CompositeUnit implements HomeComponent {

    private String name;
    private List<HomeComponent> components = new ArrayList<>();

    public CompositeUnit(String name) {
        this.name = name;
    }

    public void add(HomeComponent component) {
        components.add(component);
    }

    public void remove(HomeComponent component) {
        components.remove(component);
    }

    @Override
    public void turnOn() {
        System.out.println(name + " turning ON...");
        for (HomeComponent component : components) {
            component.turnOn();
        }
    }

    @Override
    public void turnOff() {
        System.out.println(name + " turning OFF...");
        for (HomeComponent component : components) {
            component.turnOff();
        }
    }
}

