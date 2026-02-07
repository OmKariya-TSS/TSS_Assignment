package com.tss.test;

import java.util.function.Function;

public class NonStaticMethodAsArg {
    public Integer square(Integer x) {
        return x * x;
    }

    public void doSquare(Function<Integer, Integer> function) {
        System.out.println(function.apply(5));
    }

    public static void main(String[] args) {
        NonStaticMethodAsArg object = new NonStaticMethodAsArg();
        object.doSquare(object::square);
    }
}
