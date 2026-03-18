package com.tss.entity;

public class Student{
    private int student_id;
    private int roll_number;
    private int age;
    private String name;

    public Student() {
    }

    public Student(int student_id, int roll_number, int age, String name) {
        this.student_id = student_id;
        this.roll_number = roll_number;
        this.age = age;
        this.name = name;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getRoll_number() {
        return roll_number;
    }

    public void setRoll_number(int roll_number) {
        this.roll_number = roll_number;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "student_id=" + student_id +
                ", roll_number=" + roll_number +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
