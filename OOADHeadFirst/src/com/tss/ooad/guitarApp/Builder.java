package com.tss.ooad.guitarApp;

public enum Builder {
    FENDER, MARTIN, GIBSON, COLLINGS, OLSON, RYAN, PRS;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
