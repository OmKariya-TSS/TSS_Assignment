package com.tss.dip.model;


public class MySQLLogger implements Logger {

    @Override
    public void log(String message) {
        System.out.println("MySQL Log: " + message);
    }
}
