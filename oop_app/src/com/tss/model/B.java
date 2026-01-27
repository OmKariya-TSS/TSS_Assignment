package com.tss.model;

public class B extends A{
    private int c,d;
    B(){
        System.out.println("zero args constructor");
    }

    public B(int c, int d) {
        super(30,40);
        this.c = c;
        this.d = d;
        System.out.println("two parameters constructor b");
    }
    //super class properties
//    public B(int b, int a, int c, int d) {
//        super(b, a);
//        this.c = c;
//        this.d = d;
//        System.out.println("four parameterized constructor");
//    }
}
