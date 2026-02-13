package com.tss.isp.model;

public class Robot implements Workable,Chargeable {

    @Override
    public void work() {
        System.out.println("Robot working...");
    }

    @Override
    public void charge() {
        System.out.println("Robot charging....");
    }
}
