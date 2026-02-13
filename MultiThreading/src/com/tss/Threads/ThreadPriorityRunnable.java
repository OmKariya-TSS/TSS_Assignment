package com.tss.Threads;

class PriorityTask implements Runnable {
    public void run() {
        System.out.println(
                Thread.currentThread().getName() +
                        " running | Priority: " +
                        Thread.currentThread().getPriority()
        );
    }
}

public class ThreadPriorityRunnable {
    public static void main(String[] args) {

        Thread t1 = new Thread(new PriorityTask(), "Low");
        Thread t2 = new Thread(new PriorityTask(), "Normal");
        Thread t3 = new Thread(new PriorityTask(), "High");

        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.NORM_PRIORITY);
        t3.setPriority(Thread.MAX_PRIORITY);

        t1.start();
        t2.start();
        t3.start();
    }
}
