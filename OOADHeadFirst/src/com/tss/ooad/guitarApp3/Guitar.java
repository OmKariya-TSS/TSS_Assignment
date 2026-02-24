package com.tss.ooad.guitarApp3;

public class Guitar extends Instrument {
    public Guitar(String serialNumber, double price, GuitarSpec spec) {
        super(serialNumber, price, spec);
    }

    @Override
    public GuitarSpec getSpec() {
        return (GuitarSpec) super.getSpec();
    }
}
