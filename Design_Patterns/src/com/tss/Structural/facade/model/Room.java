package com.tss.Structural.facade.model;

public class Room {
    int number;
    int numberOfBeds;

    public int getNumber() {
        return number;
    }

    public Room(int number, int numberOfBeds) {
        this.number = number;
        this.numberOfBeds = numberOfBeds;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }
}
