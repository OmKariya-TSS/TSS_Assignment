package com.tss.model;

public class A {
    private int a,b;
    A(){
        System.out.println("zero args A constructor");
    }
    public A(int b, int a) {
        this.b = b;
        this.a = a;
        System.out.println("two parameters constructor a");
    }
}
