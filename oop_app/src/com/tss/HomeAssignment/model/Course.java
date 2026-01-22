package com.tss.HomeAssignment.model;

public class Course {

    private int courseId;
    private String courseName;
    private double courseFees;
    private String duration;

    public Course(int courseId, String courseName, double courseFees, String duration) {

        if (courseId <= 0) {
            throw new IllegalArgumentException("Course ID must be positive.");
        }

        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be empty.");
        }

        if (!courseName.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Course name must contain only letters.");
        }

        if (courseFees <= 0) {
            throw new IllegalArgumentException("Course fees must be greater than zero.");
        }

        if (courseFees > 1_00_000) {
            throw new IllegalArgumentException("Course fees are unrealistically high.");
        }

        if (duration == null || duration.trim().isEmpty()) {
            throw new IllegalArgumentException("Duration cannot be empty.");
        }

        if (!duration.matches("\\d+\\s+(days|weeks|months|years)")) {
            throw new IllegalArgumentException("Invalid duration format. Example: 3 months");
        }

        this.courseId = courseId;
        this.courseName = courseName.trim();
        this.courseFees = courseFees;
        this.duration = duration.trim();
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getCourseFees() {
        return courseFees;
    }

    public String getDuration() {
        return duration;
    }


    public void setCourseId(int courseId) {
        if (courseId <= 0) {
            System.out.println("Invalid course ID.");
            return;
        }
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        if (courseName == null || courseName.trim().isEmpty()) {
            System.out.println("Course name cannot be empty.");
            return;
        }

        if (!courseName.matches("[a-zA-Z ]+")) {
            System.out.println("Course name must contain only letters.");
            return;
        }

        this.courseName = courseName.trim();
    }

    public void setCourseFees(double courseFees) {
        if (courseFees <= 0) {
            System.out.println("Course fees must be positive.");
            return;
        }

        if (courseFees > 1_00_000) {
            System.out.println("Course fees too high.");
            return;
        }

        this.courseFees = courseFees;
    }

    public void setDuration(String duration) {
        if (duration == null || duration.trim().isEmpty()) {
            System.out.println("Duration cannot be empty.");
            return;
        }

        if (!duration.matches("\\d+\\s+(days|weeks|months|years)")) {
            System.out.println("Invalid duration format. Example: 6 months");
            return;
        }

        this.duration = duration.trim();
    }

    public void displayCourse() {
        System.out.println("Course ID     : " + courseId);
        System.out.println("Course Name   : " + courseName);
        System.out.println("Course Fees   : " + courseFees);
        System.out.println("Duration      : " + duration);
        System.out.println("----------------");
    }

    public void displayCourseOverview() {
        System.out.println(courseId + " : " + courseName);
    }
}
