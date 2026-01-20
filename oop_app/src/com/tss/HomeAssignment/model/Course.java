package com.tss.HomeAssignment.model;

public class Course {
    int courseId;
    String courseName;
    double courseFees;
    String duration;
    public Course(int courseId, String courseName, double courseFees, String duration) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseFees = courseFees;
        this.duration = duration;
    }

    public double getCourseFees() {
        return courseFees;
    }
    public void setCourseFees(double courseFees) {
        this.courseFees = courseFees;
    }
    public int getCourseId() {
        return courseId;
    }
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public void displayCourse() {
        System.out.println("Course ID     : " + courseId);
        System.out.println("Course Name   : " + courseName);
        System.out.println("Course Fees   : " + courseFees);
        System.out.println("Duration      : " + duration);
        System.out.println("-------------");
    }
}
