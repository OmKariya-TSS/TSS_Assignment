package com.tss.Threads.test;

import java.util.concurrent.*;

public class SingleThreadExample {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        for (int i = 1; i <= 3; i++) {
            int task = i;
            executor.submit(() -> System.out.println("Task " + task + " executed by " + Thread.currentThread().getName()));
        }

        executor.shutdown();
    }
}
