package com.tss.ooad.guitarApp;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import java.util.*;


import java.util.*;

public class Inventory {

    private List<Guitar> guitars = new LinkedList<>();

    public void addGuitar(String serialNumber, double price,
                          GuitarSpec spec) {

        Guitar guitar = new Guitar(serialNumber, price, spec);
        guitars.add(guitar);
    }

    public List<Guitar> search(GuitarSpec searchSpec) {

        List<Guitar> matchingGuitars = new LinkedList<>();

        for (Guitar guitar : guitars) {

            if (guitar.getSpec().matches(searchSpec)) {
                matchingGuitars.add(guitar);
            }
        }

        return matchingGuitars;
    }
}
