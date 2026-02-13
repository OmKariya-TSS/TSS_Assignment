package com.tss.Creational.builder.test;

import com.tss.Creational.builder.model.User;

public class BuilderTest {
    public static void main(String[] args) {
        User user = new User.Builder("Om", 20).address("Rajkot").phone("11111111").email("om@gmail.com").build();
        System.out.println(user);
    }
}
