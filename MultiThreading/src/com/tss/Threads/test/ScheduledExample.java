package com.tss.Threads.test;

import java.util.concurrent.*;

public class ScheduledExample {
    public static void main(String[] args) {

        ScheduledExecutorService scheduler =
                Executors.newScheduledThreadPool(1);

        scheduler.schedule(() ->
                        System.out.println("Task executed after 3 seconds"),
                3, TimeUnit.SECONDS);

        scheduler.shutdown();
    }
}
