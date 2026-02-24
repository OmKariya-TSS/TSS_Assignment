package com.tss.ooad.DogDoor2;

public class BarkRecognizer {

    private DogDoor door;

    public BarkRecognizer(DogDoor door) {
        this.door = door;
    }

    public void hearBark() {
        System.out.println("BarkRecognizer: Heard a bark!");
        door.open();
    }
}
