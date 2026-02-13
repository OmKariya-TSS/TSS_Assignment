package com.tss.dip.model;


public class MongoDB implements Logger {

    @Override
    public void log(String message) {
        System.out.println("MongoDB Log: " + message);
    }
}

