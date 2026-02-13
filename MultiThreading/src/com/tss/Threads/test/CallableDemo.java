package com.tss.Threads.test;

import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<Integer> task = () -> {
            Thread.sleep(2000);
            return 100;
        };

        Future<Integer> future = executor.submit(task);

        System.out.println("Doing other work...");

        Integer result = future.get();
        System.out.println("Result: " + result);

        executor.shutdown();
    }
}
