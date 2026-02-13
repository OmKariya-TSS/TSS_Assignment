package com.tss.Threads.test;

import com.tss.Threads.model.Producer;
import com.tss.Threads.model.Q;

import com.tss.Threads.model.Consumer;

public class InterThreadCommunicationDemo {
    public static void main(String[] args) {
        Q q = new Q();

        new Producer(q);
        new Consumer(q);

        System.out.println("Press Ctrl+C to stop");
    }
}
