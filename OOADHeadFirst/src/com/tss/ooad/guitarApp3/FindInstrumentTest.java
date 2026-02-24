package com.tss.ooad.guitarApp3;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindInstrumentTest {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        // Add some guitars
        Map<String, Object> guitarProps1 = new HashMap<>();
        guitarProps1.put("builder", "Fender");
        guitarProps1.put("model", "Stratocaster");
        guitarProps1.put("type", InstrumentType.GUITAR);
        guitarProps1.put("numStrings", 6);
        guitarProps1.put("topWood", "Alder");
        guitarProps1.put("backWood", "Alder");

        GuitarSpec guitarSpec1 = new GuitarSpec(guitarProps1);
        inventory.addInstrument("G12345", 1500.0, guitarSpec1);

        Map<String, Object> guitarProps2 = new HashMap<>();
        guitarProps2.put("builder", "Gibson");
        guitarProps2.put("model", "Les Paul");
        guitarProps2.put("type", InstrumentType.GUITAR);
        guitarProps2.put("numStrings", 6);
        guitarProps2.put("topWood", "Maple");
        guitarProps2.put("backWood", "Mahogany");

        GuitarSpec guitarSpec2 = new GuitarSpec(guitarProps2);
        inventory.addInstrument("G54321", 2500.0, guitarSpec2);

        // Add some mandolins
        Map<String, Object> mandolinProps = new HashMap<>();
        mandolinProps.put("builder", "Gibson");
        mandolinProps.put("model", "F5");
        mandolinProps.put("type", InstrumentType.MANDOLIN);
        mandolinProps.put("topWood", "Spruce");
        mandolinProps.put("backWood", "Maple");

        MandolinSpec mandolinSpec = new MandolinSpec(mandolinProps);
        inventory.addInstrument("M10001", 3000.0, mandolinSpec);

        //Search for a guitar
        Map<String, Object> whatCustomerWants = new HashMap<>();
        whatCustomerWants.put("builder", "Fender");
        whatCustomerWants.put("type", InstrumentType.GUITAR);

        GuitarSpec customerSpec = new GuitarSpec(whatCustomerWants);

        List<Instrument> matchingInstruments = inventory.search(customerSpec);

        if (!matchingInstruments.isEmpty()) {
            System.out.println("We found the following matching instruments:");
            for (Instrument instrument : matchingInstruments) {
                System.out.println("  Serial#: " + instrument.getSerialNumber()
                        + ", Price: $" + instrument.getPrice()
                        + ", Model: " + instrument.getSpec().getProperty("model")
                        + ", Builder: " + instrument.getSpec().getProperty("builder")
                        + ", Type: " + instrument.getSpec().getProperty("type"));
            }
        } else {
            System.out.println("Sorry, we have nothing that matches your criteria.");
        }
    }
}
