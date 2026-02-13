package com.tss.Threads.model;

public class Q {
    int num;
    boolean valueSet = false;

    synchronized void put(int num) {
        while (valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        this.num = num;
        valueSet = true;
        System.out.println("Produced: " + num);

        notify();
    }

    synchronized int get() {
        while (!valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        System.out.println("Consumed: " + num);
        valueSet = false;

        notify();
        return num;
    }
}
