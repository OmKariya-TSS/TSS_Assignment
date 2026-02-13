package com.tss.Threads.test;

import com.tss.Threads.model.Worker;

public class ThreadDemo {

    public static void main(String[] args) throws Exception {

        Worker t1 = new Worker("Thread-1");
        Worker t2 = new Worker("Thread-2");
        Worker t3 = new Worker("Thread-3");

        t1.start();
        t2.start();
        t3.start();

        System.out.println("t1 is alive? " + t1.isAlive());
        System.out.println("t2 is alive? " + t2.isAlive());
        System.out.println("t3 is alive? " + t3.isAlive());

        t1.join();
        t2.join();
        t3.join();

        System.out.println("After join:");
        System.out.println("t1 is alive? " + t1.isAlive());
        System.out.println("t2 is alive? " + t2.isAlive());
        System.out.println("t3 is alive? " + t3.isAlive());

        System.out.println("Main thread finished");
    }
}
