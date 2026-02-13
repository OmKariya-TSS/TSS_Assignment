package com.tss.Threads;

public class Threads {
    public static void main(String[] args) {

        Thread t = Thread.currentThread();

        System.out.println("Thread Name: " + t.getName());
        System.out.println("Priority: " + t.getPriority());
        System.out.println("State: " + t.getState());

        t.setName("MyMainThread");
        System.out.println("New Name: " + t.getName());

    }
}
