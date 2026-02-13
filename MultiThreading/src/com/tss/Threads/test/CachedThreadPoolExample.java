package com.tss.Threads.test;

import java.util.concurrent.*;

public class CachedThreadPoolExample {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 1; i <= 10; i++) {
            int task = i;
            executor.submit(() -> System.out.println("Task " + task + " by " + Thread.currentThread().getName()));
        }

        executor.shutdown();
    }
}
