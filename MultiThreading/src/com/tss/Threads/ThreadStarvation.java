package com.tss.Threads;

class StarvationDemo extends Thread {
    public void run() {
        while (true) {
            System.out.println(getName());
        }
    }

    public static void main(String[] args) {
        StarvationDemo low = new StarvationDemo();
        StarvationDemo high = new StarvationDemo();

        low.setName("LOW");
        high.setName("HIGH");

        low.setPriority(Thread.MIN_PRIORITY);
        high.setPriority(Thread.MAX_PRIORITY);

        low.start();
        high.start();
    }
}
