package com.tss.dip.model;


public class PostgresLogger implements Logger {

    @Override
    public void log(String message) {
        System.out.println("Postgres Log: " + message);
    }
}
