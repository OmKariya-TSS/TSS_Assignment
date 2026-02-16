package com.tss.Structural.bridge;

class Remote {

    protected Device device;

    public Remote(Device device) {
        this.device = device;
    }

    public void powerOn() {
        device.turnOn();
    }

    public void powerOff() {
        device.turnOff();
    }

    public void setVolume(int percent) {
        device.setVolume(percent);
    }
}
