package com.tss.HomeAssignment.service;

import java.util.Scanner;
import com.tss.HomeAssignment.model.Course;

public class CourseService {

    Course[] courses;
    Scanner scanner = new Scanner(System.in);

    public CourseService(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Course size must be greater than zero.");
        }
        courses = new Course[size];
    }


    public boolean isCourseFull() {
        for (Course c : courses) {
            if (c == null) {
                return false;
            }
        }
        return true;
    }


    public void createCourse() {

        if (isCourseFull()) {
            System.out.println(" Course limit reached. Cannot add more courses.");
            return;
        }

        String name = getValidCourseName();
        double fees = getValidCourseFees();
        String duration = getValidDuration();

        int courseId = generateCourseId();
        Course course = new Course(courseId, name, fees, duration);

        for (int i = 0; i < courses.length; i++) {
            if (courses[i] == null) {
                courses[i] = course;
                System.out.println("Course created successfully. ID: " + courseId);
                break;
            }
        }
    }


    private String getValidCourseName() {
        while (true) {
            System.out.print("Enter Course Name: ");
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("Course name cannot be empty.");
                continue;
            }

            if (!name.matches("[a-zA-Z ]+")) {
                System.out.println("Course name must contain only letters.");
                continue;
            }

            if (isDuplicateCourse(name)) {
                System.out.println(" Course already exists.");
                continue;
            }

            return name;
        }
    }

    private double getValidCourseFees() {
        while (true) {
            System.out.print("Enter Course Fees: ");
            String input = scanner.nextLine().trim();

            try {
                double fees = Double.parseDouble(input);

                if (fees <= 0) {
                    System.out.println("Fees must be greater than zero.");
                } else if (fees > 1_00_000) {
                    System.out.println("Fees too high. Enter realistic amount.");
                } else {
                    return fees;
                }

            } catch (NumberFormatException e) {
                System.out.println("Enter a valid numeric value.");
            }
        }
    }

    private String getValidDuration() {
        while (true) {
            System.out.print("Enter Duration (e.g., 3 months): ");
            String duration = scanner.nextLine().trim();

            if (duration.isEmpty()) {
                System.out.println("Duration cannot be empty.");
                continue;
            }

            if (!duration.matches("\\d+\\s+(days|weeks|months|years)")) {
                System.out.println(" Invalid format. Example: 6 months");
                continue;
            }

            return duration;
        }
    }

    private boolean isDuplicateCourse(String name) {
        for (Course c : courses) {
            if (c != null && c.getCourseName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }


    public void displayAllCourses() {
        boolean found = false;
        for (Course c : courses) {
            if (c != null) {
                c.displayCourse();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No courses available.");
        }
    }

    public void displayAvailableCourses() {
        boolean found = false;
        for (Course c : courses) {
            if (c != null) {
                c.displayCourseOverview();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No courses available.");
        }
    }


    public Course getCourseById(int id) {
        if (id <= 0) {
            System.out.println("Invalid course ID.");
            return null;
        }

        for (Course c : courses) {
            if (c != null && c.getCourseId() == id) {
                return c;
            }
        }

        System.out.println("Course not found.");
        return null;
    }


    private int generateCourseId() {
        int id;
        boolean exists;

        do {
            id = (int) (Math.random() * 9000) + 1000;
            exists = false;

            for (Course c : courses) {
                if (c != null && c.getCourseId() == id) {
                    exists = true;
                    break;
                }
            }
        } while (exists);

        return id;
    }
}
