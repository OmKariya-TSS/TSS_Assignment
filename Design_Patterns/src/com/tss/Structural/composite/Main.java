package com.tss.Structural.composite;

import com.tss.Structural.composite.compositeComponents.CompositeUnit;
import com.tss.Structural.composite.leaf.Appliance;

public class Main {
    public static void main(String[] args) {

        Appliance light = new Appliance("Light");
        Appliance fan = new Appliance("Fan");
        Appliance ac = new Appliance("AC");

        CompositeUnit room1 = new CompositeUnit("Room 1");
        room1.add(light);
        room1.add(fan);

        CompositeUnit room2 = new CompositeUnit("Room 2");
        room2.add(ac);

        CompositeUnit floor1 = new CompositeUnit("Floor 1");
        floor1.add(room1);
        floor1.add(room2);

        CompositeUnit house = new CompositeUnit("House");
        house.add(floor1);

        house.turnOn();

        System.out.println("\n---\n");

        room1.turnOff();
    }
}
