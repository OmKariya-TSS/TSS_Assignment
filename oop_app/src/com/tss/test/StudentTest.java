package com.tss.test;

import com.tss.model.Student;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Student OOP App");
        Student student = new Student();
        System.out.println("Enter details of student:");
        int id;
        while (true) {
            try {
                System.out.println("Enter ID of student (positive number only):");
                id = scanner.nextInt();
                student.setId(id);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! ID must be a number.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Try again.");
            }
        }

        String name;
        while (true) {
            try {
                System.out.println("Enter name of student:");
                name = scanner.next();
                student.setName(name);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Try again.");
            }
        }
        String course;
        while (true) {
            try {
                System.out.println("Enter course of student:");
                course = scanner.next();
                student.setCourse(course);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Try again.");
            }
        }
        long totalFees;
        while (true) {
            try {
                System.out.println("Enter total fees of student:");
                totalFees = scanner.nextLong();
                student.setTotalFees(totalFees);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Enter a number.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Try again.");
            }
        }

        long feesPaid;
        while (true) {
            try {
                System.out.println("Enter fees paid by student:");
                feesPaid = scanner.nextLong();
                student.setFeesPaid(feesPaid);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Enter a number.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Try again.");
            }
        }
        student = new Student(id,name,course,feesPaid,totalFees);
        System.out.println("\nStudent created successfully!");
        studentMenu(student);
    }
    public static void studentMenu(Student student){
        Scanner scanner = new Scanner(System.in);
        System.out.println("the menu is given below :");
        System.out.println("enter 1 for displaying student");
        System.out.println("enter 2 for paying fees");
        System.out.println("enter 3 for viewing pending fees");
        System.out.println("enter 4 for updating course");
        System.out.println("enter 5 for exit");
        int n = scanner.nextInt();
        switch(n){
            case 1:
                student.displayProfile(student);
                studentMenu(student);
                break;
            case 2:
                long amount;
                while (true) {
                    try {
                        System.out.println("\nEnter amount to pay:");
                        amount = scanner.nextLong();
                        student.payFees(amount);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input! Enter a number.");
                        scanner.nextLine();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                studentMenu(student);
                break;
            case 3:
                long pending = student.getPendingFees();
                System.out.println("pending fees are:"+pending);
                studentMenu(student);
                break;
            case 4:
                String oldCourse = student.getCourse();
                System.out.println("old course was :"+oldCourse);
                String updated;
                while (true) {
                    try {
                        System.out.println("Enter course of student:");
                        updated = scanner.next();
                        student.setCourse(updated);
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage() + " Try again.");
                    }
                }
                System.out.println("new course is :"+student.getCourse());
                studentMenu(student);
                break;
            case 5:
                System.out.println("you are on exit");
                break;
            default:
                System.out.println("please enter values within 1 to 5");
                studentMenu(student);
                break;
        }

    }
}
