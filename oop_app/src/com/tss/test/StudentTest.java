package com.tss.test;

import com.tss.model.Student;

import java.util.Scanner;

public class StudentTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Student OOP App");
        Student student = new Student();
        System.out.println("Enter details of student:");
        int id = student.setId(scanner);
        System.out.println("Enter name of student:");
        String nonName = scanner.next();
        String name= student.setName(nonName,scanner);
        System.out.println("Enter course of student:");
        String nonCourse = scanner.next();
        String course = student.setCourse(nonCourse,scanner);
        System.out.println("Enter total fees of student:");
        long nonTotalFees = scanner.nextLong();
        long totalFees = student.setTotalFees(nonTotalFees,scanner);
        System.out.println("Enter fees paid by student:");
        long nonFeesPaid = scanner.nextLong();
        long feesPaid=student.setFeesPaid(nonFeesPaid,scanner);
        student = new Student(id,name,course,feesPaid,totalFees);
        System.out.println("\nStudent created successfully!");
        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Course: " + student.getCourse());
        System.out.println("Fees Paid: " + student.getFeesPaid());
        System.out.println("Total Fees: " + student.getTotalFees());
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
                System.out.println("\nEnter amount to pay:");
                long amount = scanner.nextLong();
                long updatedFees = student.payFees(amount, scanner);
                System.out.println("Updated fees paid: " + updatedFees);
                studentMenu(student);
                break;
            case 3:
                long pending = student.getPendingFees();
                System.out.println("pending fees are:"+pending);
                studentMenu(student);
                break;
            case 4:
                System.out.println("enter updated course :");
                String updated = scanner.next();
                String oldCourse = student.getCourse();
                System.out.println("old course was :"+oldCourse);
                String newCourse = student.setCourse(updated,scanner);
                System.out.println("new course is :"+newCourse);
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
