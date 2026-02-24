package com.tss.ooad.DogDoor2;

public class DogDoorSimulator {

    public static void main(String[] args) {

        DogDoor door = new DogDoor();
        Remote remote = new Remote(door);
        BarkRecognizer recognizer = new BarkRecognizer(door);

        System.out.println("Fido barks to go outside...");
        recognizer.hearBark();

        System.out.println("\nFido has gone outside...");
        System.out.println("Fido's all done...");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) { }

        System.out.println("\nFido barks to come back inside...");
        recognizer.hearBark();

        System.out.println("\nFido's back inside...");
    }
}
