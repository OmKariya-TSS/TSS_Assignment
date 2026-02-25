package com.tss.Evaluation.model;


class Animal {

    void sound() {

        System.out.println("Animal sound");

    }

}

class Dog extends Animal {

    void play() {

        System.out.println("Dog plays");

    }

}

class Cat extends Animal {

}

public class Test {

    public static void main(String[] args) {

        Animal a1 = new Dog();

        Animal a2 = new Cat();

        Dog d1 = (Dog) a1;

        d1.play();

        Dog d2 = (Dog) a2;   // What happens here?

        d2.play();

    }

}

