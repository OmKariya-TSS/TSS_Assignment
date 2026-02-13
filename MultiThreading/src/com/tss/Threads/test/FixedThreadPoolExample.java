package com.tss.Threads.test;

import java.util.concurrent.*;

public class FixedThreadPoolExample {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 6; i++) {
            int task = i;
            executor.submit(() -> {System.out.println("Task " + task + " by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000);
                }
                catch (Exception e) {}
            });
        }

        executor.shutdown();
    }
}
