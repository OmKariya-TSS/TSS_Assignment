package com.tss.Structural.bridge;

public class Radio implements Device{
    @Override
    public void turnOn() {
        System.out.println("radio turned on");
    }

    @Override
    public void turnOff() {
        System.out.println("radio turned off");
    }

    @Override
    public void setVolume(int percent) {
        System.out.println("radio volume set to "+percent);
    }
}
