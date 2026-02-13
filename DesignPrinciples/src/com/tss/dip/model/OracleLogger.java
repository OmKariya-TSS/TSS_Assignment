package com.tss.dip.model;


public class OracleLogger implements Logger {

    @Override
    public void log(String message) {
        System.out.println("Oracle Log: " + message);
    }
}
