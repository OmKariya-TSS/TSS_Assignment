package com.tss.Structural.bridge;

public class Main {
    public static void main(String[] args) {

        Device tv = new TV();
        Remote remote = new Remote(tv);
        remote.powerOn();
        remote.setVolume(50);

        System.out.println();

        Device radio = new Radio();
        AdvancedRemote advancedRemote = new AdvancedRemote(radio);

        advancedRemote.powerOn();
        advancedRemote.mute();
    }
}

