package com.tss.streams;

import java.util.Arrays;
import java.util.List;

public class ListDemo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(5,1,3,2,4);
        //even elements
        System.out.println("even numbers");
        list.stream().filter((num)->num%2==0).forEach((num)-> System.out.println(num));
        //even numbers square
        System.out.println("even numbers square");
        list.stream().filter((num)->num%2==0).map((num)->num*num).forEach((num)-> System.out.println(num));
        //square using map
        System.out.println("squares");
        list.stream().map((num)->num*num).forEach((num)-> System.out.println(num));
        list.stream()
                .peek(n -> System.out.println("Before: " + n))
                .filter(n -> n > 3)
                .peek(n -> System.out.println("After: " + n))
                .forEach(System.out::println);

    }
}
