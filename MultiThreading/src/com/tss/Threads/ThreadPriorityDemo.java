package com.tss.Threads;


class MyTask extends Thread {

    MyTask(String name, int priority) {
        setName(name);
        setPriority(priority);
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(getName() + " running | Priority: " + getPriority());
        }
    }
}

public class ThreadPriorityDemo {
    public static void main(String[] args) {

        MyTask t1 = new MyTask("LowPriorityThread", Thread.MIN_PRIORITY);
        MyTask t2 = new MyTask("NormalPriorityThread", Thread.NORM_PRIORITY);
        MyTask t3 = new MyTask("HighPriorityThread", Thread.MAX_PRIORITY);

        t1.start();
        t2.start();
        t3.start();
    }
}

