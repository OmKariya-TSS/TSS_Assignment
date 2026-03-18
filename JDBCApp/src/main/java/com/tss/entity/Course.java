package com.tss.entity;

public class Course {
    private int courseId;
    private String name;
    private double fees;

    public Course() {

    }

    public int getCourseId() {
        return courseId;
    }

    public Course(int courseId, String name, double fees) {
        this.courseId = courseId;
        this.name = name;
        this.fees = fees;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", name='" + name + '\'' +
                ", fees=" + fees +
                '}';
    }
}
