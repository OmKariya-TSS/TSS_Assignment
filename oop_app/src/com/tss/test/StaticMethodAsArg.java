package com.tss.test;

import java.util.function.Function;

public class StaticMethodAsArg {

    static Integer square(Integer x) {
        return x * x;
    }

    static void doSquare(Function<Integer, Integer> func) {
        System.out.println(func.apply(5));
    }

    public static void main(String[] args) {
        doSquare(StaticMethodAsArg::square);
    }
}
