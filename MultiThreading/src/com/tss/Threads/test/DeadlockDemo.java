package com.tss.Threads.test;

class A {
    synchronized void methodA(B b) {
        System.out.println(Thread.currentThread().getName() + " locked A");
        try { Thread.sleep(1000); } catch (Exception e) {}
        System.out.println(Thread.currentThread().getName() + " trying to lock B");
        b.last();
    }

    synchronized void last() {
        System.out.println("Inside A.last()");
    }
}

class B {
    synchronized void methodB(A a) {
        System.out.println(Thread.currentThread().getName() + " locked B");
        try { Thread.sleep(1000); } catch (Exception e) {}
        System.out.println(Thread.currentThread().getName() + " trying to lock A");
        a.last();
    }

    synchronized void last() {
        System.out.println("Inside B.last()");
    }
}

public class DeadlockDemo {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();

        Thread t1 = new Thread(() -> a.methodA(b), "Thread-1");
        Thread t2 = new Thread(() -> b.methodB(a), "Thread-2");

        t1.start();
        t2.start();
    }
}
