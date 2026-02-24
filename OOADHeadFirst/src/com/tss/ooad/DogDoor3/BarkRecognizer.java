package com.tss.ooad.DogDoor3;

public class BarkRecognizer {

    private DogDoor door;

    public BarkRecognizer(DogDoor door) {
        this.door = door;
    }

    public void recognize(Bark bark) {
        System.out.println("BarkRecognizer: Heard a '" + bark.getSound() + "'");

        for (Bark allowedBark : door.getAllowedBarks()) {
            if (allowedBark.equals(bark)) {
                System.out.println("This is the correct dog!");
                door.open();
                return;
            }
        }

        System.out.println("This dog is not allowed.");
    }
}
