package com.tss.ooad.guitarApp2;

public class Mandolin extends Instrument {
    public Mandolin(String serialNumber, double price, MandolinSpec spec) {
        super(serialNumber, price, spec);
    }

    @Override
    public MandolinSpec getSpec() {
        return (MandolinSpec) super.getSpec();
    }
}
