package com.tss.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class StringDemo {
    public static void main(String[] args) {
        String[] names= {"Jayesh","Nimesh","Mark","Mahesh","Ramesh"};
        System.out.println("ascending order first 3 students");
        Arrays.stream(names).sequential().limit(3).sorted().forEach((name)-> System.out.println(name));
        System.out.println("sorted in ascending order if contains 'a' ");
        Arrays.stream(names).sequential().limit(3).filter((name)->name.contains("a")).sorted().forEach((name)-> System.out.println(name));
        System.out.println("descending order");
        Arrays.stream(names).sequential().sorted(Comparator.reverseOrder()).forEach((name)-> System.out.println(name));
        System.out.println("first three characterrs");
        Arrays.stream(names).sequential().map((name)->name.substring(0,3)).forEach((name)-> System.out.println(name));
        System.out.println("less than equal 4 chars");
        Arrays.stream(names).sequential().filter((name)->name.length()<=4).forEach((name)-> System.out.println(name));
    }
}
