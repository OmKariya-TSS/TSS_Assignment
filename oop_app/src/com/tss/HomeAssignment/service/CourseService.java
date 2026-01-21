package com.tss.HomeAssignment.service;

import java.util.Scanner;
import com.tss.HomeAssignment.model.Course;
import com.tss.HomeAssignment.model.Student;

public class CourseService {
    Course[] courses;
    Scanner scanner = new Scanner(System.in);
    public CourseService(int size) {
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
            System.out.println("Course limit reached. Cannot add more courses.");
            return;
        }
        String name = "";
        double fees = 0;
        String duration = "";
        while (true) {
            try {
                System.out.print("Enter Course Name: ");
                name = scanner.nextLine().trim();

                if (name.isEmpty()) {
                    System.out.println("Error: Course name cannot be empty.");
                    continue;
                }

                if (name.matches(".*\\d.*")) {
                    System.out.println("Error: Course name cannot contain numbers.");
                    continue;
                }

                break;
            } catch (Exception e) {
                System.out.println("Unexpected error. Try again.");
                scanner.nextLine();
            }
        }
        while (true) {
            try {
                System.out.print("Enter Course Fees: ");
                String input = scanner.nextLine().trim();
                fees = Double.parseDouble(input);

                if (fees <= 0) {
                    System.out.println("Error: Fees must be a positive number.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number for fees.");
            } catch (Exception e) {
                System.out.println("Unexpected error. Please try again.");
                scanner.nextLine();
            }
        }
        while (true) {
            try {
                System.out.print("Enter Duration: ");
                duration = scanner.nextLine().trim();
                if (duration.isEmpty()) {
                    System.out.println("Error: Duration cannot be empty.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Unexpected error. Please try again.");
                scanner.nextLine();
            }
        }
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
    public Course getCourseById(int id) {
        for (Course c : courses) {
            if (c != null && c.getCourseId() == id) {
                return c;
            }
        }
        return null;
    }
    private int generateCourseId() {
        int id;
        boolean exists;
        do {
            id = (int) (Math.random() * 9) + 1;
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
