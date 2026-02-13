package com.tss.Evaluation;

import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.*;
import java.util.Arrays;

public class Student {
    int id;
    String name;
    int[] marks = new int[3];


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name==null){
            throw new IllegalArgumentException("name cant be empty");
        }
        if(!name.matches("[a-zA-Z]+")){
            throw new IllegalArgumentException("name should contain only letters");
        }
        this.name = name;
    }

    public int[] getMarks() {
        return marks;
    }

    public void setMarks(int[] marks) {
        for(int mark : marks){
            if(mark<0){
                throw new IllegalArgumentException("marks can not be negative");
            }
            else if(mark>30){
                throw new IllegalArgumentException("marks range is only between 1 to 30");
            }
        }
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", marks=" + Arrays.toString(marks) +
                '}';
    }

}
