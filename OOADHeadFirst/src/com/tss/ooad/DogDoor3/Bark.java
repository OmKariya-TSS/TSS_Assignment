package com.tss.ooad.DogDoor3;

public class Bark {

    private String sound;

    public Bark(String sound) {
        this.sound = sound;
    }

    public String getSound() {
        return sound;
    }

    // Compare barks
    public boolean equals(Object o) {
        if (o instanceof Bark) {
            Bark otherBark = (Bark) o;
            return this.sound.equalsIgnoreCase(otherBark.getSound());
        }
        return false;
    }

    public int hashCode() {
        return sound.toLowerCase().hashCode();
    }
}

