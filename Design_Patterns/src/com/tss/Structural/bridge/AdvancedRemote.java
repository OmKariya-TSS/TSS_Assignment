package com.tss.Structural.bridge;

class AdvancedRemote extends Remote {

    public AdvancedRemote(Device device) {
        super(device);
    }

    public void mute() {
        device.setVolume(0);
        System.out.println("Device muted");
    }
}
