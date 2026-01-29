package com.tempPackage2;

import java.util.Arrays;

public class Student {
    private int id;
    private String name;
    private Department dept;
    private int[] marks;
//    boolean valid;

    public Student(int id,String name, Department dept,int[] marks){


        this.id=id;
        this.name=name;
        this.dept=dept;
        this.marks=marks;

    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dept=" + dept +
                ", marks=" + Arrays.toString(marks) +
                '}';
    }
//
    public double average(){
        double sum=0;
        for(int mark:this.marks){
            sum+=mark;
        }

        return sum/this.marks.length;
    }




}
