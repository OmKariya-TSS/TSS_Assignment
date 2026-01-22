package com.tss.HomeAssignment.service;

import java.util.Scanner;

import com.tss.HomeAssignment.model.Course;
import com.tss.HomeAssignment.model.Student;

public class StudentService {
    Student[] students;
    Scanner scanner = new Scanner(System.in);
    public StudentService(int size) {
        students = new Student[size];
    }
    public boolean isStudentArrayFull() {
        for (Student s : students) {
            if (s == null) {
                return false;
            }
        }
        return true;
    }
    public int generateUniqueStudentId() {
        int id;
        boolean exists;
        do {
            id = (int) (Math.random() * 10) + 1;
            exists = false;
            for (Student s : students) {
                if (s != null && s.getStudentId() == id) {
                    exists = true;
                    break;
                }
            }
        } while (exists);
        return id;
    }
    public void createStudent() {
        if (isStudentArrayFull()) {
            System.out.println("Student limit reached. Cannot add more students.");
            return;
        }
        String name = "";
        while (true) {
            try {
                System.out.print("Enter Student Name: ");
                name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    System.out.println("Error: Name cannot be empty. Please enter a valid name.");
                    continue;
                }
                if (name.matches(".*\\d.*")) {
                    System.out.println("Error: Name cannot contain numbers. Try again.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Unexpected error. Please try again.");
                scanner.nextLine();
            }
        }
        int id = generateUniqueStudentId();
        Student student = new Student(id, name);
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = student;
                System.out.println("Student created successfully. ID: " + id);
                break;
            }
        }
    }
    public void displayAllStudents() {
        boolean found = false;
        for (Student s : students) {
            if (s != null) {
                s.displayProfile();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No students found.");
        }
    }
    public Student getStudentById(int id) {
        for (Student s : students) {
            if (s != null && s.getStudentId() == id) {
                return s;
            }
        }
        return null;
    }
    public void displayAvailableStudents() {
        for (Student s : students) {
            if (s != null) {
                s.displayOverviewOfStudent();
            }
        }
    }
    public void unenrollCourseFromStudent() {
        System.out.print("Enter Student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());
        Student student = getStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.print("Enter Course ID to remove: ");
        int courseId = Integer.parseInt(scanner.nextLine());
        student.removeCourse(courseId);
    }
    public void replaceStudentCourse(CourseService courseService) {
        System.out.print("Enter Student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());
        Student student = getStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.print("Enter Old Course ID: ");
        int oldCourseId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter New Course ID: ");
        int newCourseId = Integer.parseInt(scanner.nextLine());
        Course newCourse = courseService.getCourseById(newCourseId);
        if (newCourse == null) {
            System.out.println("New course not found.");
            return;
        }
        student.replaceCourse(oldCourseId, newCourse);
    }
}
