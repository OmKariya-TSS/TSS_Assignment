package com.tss.ooad.DogDoor3;

public class DogDoorTestDrive {

    public static void main(String[] args) {

        DogDoor door = new DogDoor();

        // Add multiple allowed barks
        door.addAllowedBark(new Bark("rowlf"));
        door.addAllowedBark(new Bark("rooowlf"));
        door.addAllowedBark(new Bark("rawlf"));
        door.addAllowedBark(new Bark("woof"));

        BarkRecognizer recognizer = new BarkRecognizer(door);
        Remote remote = new Remote(door);

        System.out.println("Bruce starts barking...");
        recognizer.recognize(new Bark("rowlf"));

        try {
            Thread.sleep(10000);
        } catch (Exception e) {}

        System.out.println("\nA small dog starts barking...");
        recognizer.recognize(new Bark("yip"));

        System.out.println("\nOwner presses remote...");
        remote.pressButton();
    }
}
