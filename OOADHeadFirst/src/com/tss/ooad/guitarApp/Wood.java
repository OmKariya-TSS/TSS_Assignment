package com.tss.ooad.guitarApp;
public enum Wood {
    INDIAN_ROSEWOOD, BRAZILIAN_ROSEWOOD,
    MAHOGANY, MAPLE, COCOBOLO, CEDAR,
    ADIRONDACK, ALDER, SITKA;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
