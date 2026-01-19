package com.tss.test;

import com.tss.model.Student;

import java.util.Scanner;

public class StudentTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("welcome to student oop app");
        System.out.println("enter details of student :");
        System.out.println("enter id of student :");
        int id = scanner.nextInt();
        System.out.println("enter name of student :");
        String name = scanner.next();
        System.out.println("enter course of student :");
        String course = scanner.next();
        System.out.println("enter fees paid by student :");
        long feesPaid = scanner.nextLong();
        System.out.println("enter total fees of student :");
        long totalFees = scanner.nextLong();
        Student student = new Student(id,name,course,feesPaid,totalFees);
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
                System.out.println("enter amount to pay :");
                long amount = scanner.nextLong();
                student.payFees(amount);
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
                student.setCourse(updated);
                System.out.println("new course is :"+updated);
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
