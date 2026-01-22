package com.tss.HomeAssignment.service;

import java.util.Scanner;
import com.tss.HomeAssignment.model.Student;

public class StudentService {

    Student[] students;
    Scanner scanner = new Scanner(System.in);

    public StudentService(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Student size must be greater than zero.");
        }
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
            id = (int) (Math.random() * 9) + 1;
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
            System.out.println(" Student limit reached. Cannot add more students.");
            return;
        }

        String name = getValidStudentName();
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


    private String getValidStudentName() {
        while (true) {
            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println(" Name cannot be empty.");
                continue;
            }

            if (!name.matches("[a-zA-Z ]+")) {
                System.out.println("Name must contain only letters.");
                continue;
            }

            if (isDuplicateStudent(name)) {
                System.out.println("Student with this name already exists.");
                continue;
            }

            return name;
        }
    }

    private boolean isDuplicateStudent(String name) {
        for (Student s : students) {
            if (s != null && s.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
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

    public void displayAvailableStudents() {
        boolean found = false;

        for (Student s : students) {
            if (s != null) {
                s.displayOverviewOfStudent();
                found = true;
            }
        }

        if (!found) {
            System.out.println(" No students available.");
        }
    }

    public Student getStudentById(int id) {

        if (id <= 0) {
            System.out.println("Invalid student ID.");
            return null;
        }

        for (Student s : students) {
            if (s != null && s.getStudentId() == id) {
                return s;
            }
        }

        System.out.println("Student not found.");
        return null;
    }
}
