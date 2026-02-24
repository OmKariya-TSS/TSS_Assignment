package com.tss.ooad.DogDoor2;

public class DogDoor {

    private boolean open;

    public DogDoor() {
        this.open = false;
    }

    public void open() {
        if (!open) {
            System.out.println("The dog door opens.");
            open = true;

            new Thread(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) { }
                close();
            }).start();
        }
    }

    public void close() {
        if (open) {
            System.out.println("The dog door closes.");
            open = false;
        }
    }

    public boolean isOpen() {
        return open;
    }
}
