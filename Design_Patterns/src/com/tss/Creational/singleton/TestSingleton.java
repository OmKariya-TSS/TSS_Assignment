package com.tss.Creational.singleton;

public class TestSingleton {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        System.out.println(instance);
    }
}
