package com.tss.Structural.facade.model;

public class Luggage {
    String name;
    int id;
    boolean isDropped;

    public Luggage(String name, boolean isDropped, int id) {
        this.name = name;
        this.isDropped = isDropped;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDropped() {
        return isDropped;
    }

    public void setDropped(boolean dropped) {
        isDropped = dropped;
    }
}
