package com.tss.ooad.guitarApp;

import java.util.List;
import java.util.*;

public class FindGuitarTester {

    public static void main(String[] args) {

        Inventory inventory = new Inventory();
        initializeInventory(inventory);

        GuitarSpec whatErinLikes =
                new GuitarSpec(Builder.FENDER, "Stratocaster",
                        Type.ELECTRIC, 6,
                        Wood.ALDER, Wood.ALDER);

        List<Guitar> matchingGuitars =
                inventory.search(whatErinLikes);

        if (!matchingGuitars.isEmpty()) {

            System.out.println("Erin, you might like these guitars:");

            for (Guitar guitar : matchingGuitars) {

                GuitarSpec spec = guitar.getSpec();

                System.out.println("We have a "
                        + spec.getBuilder() + " "
                        + spec.getModel() + " "
                        + spec.getNumStrings() + "-string "
                        + spec.getType() + " guitar:\n   "
                        + spec.getBackWood() + " back and sides,\n   "
                        + spec.getTopWood() + " top.\n"
                        + "You can have it for only $"
                        + guitar.getPrice() + "!\n----");
            }
        } else {
            System.out.println("Sorry, Erin, we have nothing for you.");
        }
    }

    private static void initializeInventory(Inventory inventory) {

        inventory.addGuitar("V95693", 1499.95,
                new GuitarSpec(Builder.FENDER, "Stratocaster",
                        Type.ELECTRIC, 6,
                        Wood.ALDER, Wood.ALDER));

        inventory.addGuitar("V9512", 1549.95,
                new GuitarSpec(Builder.FENDER, "Stratocaster",
                        Type.ELECTRIC, 6,
                        Wood.ALDER, Wood.ALDER));

        inventory.addGuitar("X12345", 1999.99,
                new GuitarSpec(Builder.GIBSON, "Les Paul",
                        Type.ELECTRIC, 6,
                        Wood.MAHOGANY, Wood.MAPLE));
    }
}
