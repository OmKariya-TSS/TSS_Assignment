package com.tss.model;

public class StudentCompare implements Comparable<StudentCompare>{
    private int id ;
    private String name;

    public StudentCompare(int id, String name) {
        this.id = id;
        this.name = name;
    }

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
        this.name = name;
    }

    @Override
    public String toString() {
        return "StudentCompare{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(StudentCompare o) {
        return o.id-this.id;
    }
}
