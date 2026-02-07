package com.tss.test;

import java.time.LocalDate;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterface {
    public static void main(String[] args) {
        BiFunction<Integer,Integer,Integer> bifunction = (a,b)-> a+b;
        System.out.println(bifunction.apply(2,4));
        Consumer<Integer> consumer = (a)-> System.out.println(a*a);
        consumer.accept(10);
        Supplier<LocalDate> supplier = LocalDate::now;
        System.out.println(supplier.get());
        Predicate<Integer> predicate = (a)->a%2!=0;
        System.out.println(predicate.test(10));
   }
}
