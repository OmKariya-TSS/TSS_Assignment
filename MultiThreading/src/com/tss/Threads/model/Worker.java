package com.tss.Threads.model;

public class Worker extends Thread {

    String name;

    public Worker(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println(name + " started");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println(name + " finished");
    }
}
