package com.tss.ooad.guitarApp;

public enum Type {
    ACOUSTIC, ELECTRIC;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}

