package com.tss.Threads;

    class Worker extends Thread {
        public void run() {
            synchronized (this) {
                System.out.println("Worker thread working...");
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {}

                System.out.println("Worker thread notifying...");
                this.notify();
            }
        }
    }

    public class ThreadsWait {
        public static void main(String[] args) throws Exception {

            Worker w = new Worker();

            synchronized (w) {
                System.out.println("Main thread waiting...");
                w.start();
                w.wait();
            }

            System.out.println("Main thread resumed");
        }
    }


