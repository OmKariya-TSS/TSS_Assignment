package com.tss.Creational.singleton;

public class Singleton {
    private static Singleton instance ;
    private Singleton(){}

    private static class Helper{
        private static final Singleton instance = new Singleton();
    }
    public static Singleton getInstance() {
        return Helper.instance;
    }
}
