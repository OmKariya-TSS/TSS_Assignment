package com.tss.Structural.bridge;

public class TV implements Device{
    @Override
    public void turnOn() {
        System.out.println("tv turned on");
    }

    @Override
    public void turnOff() {
        System.out.println("tv  turned off");
    }

    @Override
    public void setVolume(int percent) {
        System.out.println("tv volume set to "+ percent);
    }
}
