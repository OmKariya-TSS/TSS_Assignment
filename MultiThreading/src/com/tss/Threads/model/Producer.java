package com.tss.Threads.model;

public class Producer implements Runnable {
    Q q;

    public Producer(Q q) {
        this.q = q;
        new Thread(this, "Producer").start();
    }

    public void run() {
        int i = 1;
        while (true) {
            q.put(i++);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {}
        }
    }
}
