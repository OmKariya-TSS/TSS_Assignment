package com.tss.ooad.guitarApp2;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Instrument> instruments;

    public Inventory() {
        instruments = new ArrayList<>();
    }

    public void addInstrument(String serialNumber, double price, InstrumentSpec spec) {
        if (spec instanceof GuitarSpec) {
            instruments.add(new Guitar(serialNumber, price, (GuitarSpec) spec));
        } else if (spec instanceof MandolinSpec) {
            instruments.add(new Mandolin(serialNumber, price, (MandolinSpec) spec));
        }
    }

    public Instrument getInstrument(String serialNumber) {
        for (Instrument instrument : instruments) {
            if (instrument.getSerialNumber().equals(serialNumber)) {
                return instrument;
            }
        }
        return null;
    }

    public List<Instrument> search(InstrumentSpec searchSpec) {
        List<Instrument> matchingInstruments = new ArrayList<>();
        for (Instrument instrument : instruments) {
            if (instrument.getSpec().matches(searchSpec)) {
                matchingInstruments.add(instrument);
            }
        }
        return matchingInstruments;
    }
}
