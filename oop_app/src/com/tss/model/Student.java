package com.tss.model;

import java.util.Scanner;

public class Student {
    private int id ;
    private String name;
    private String course;
    private long feesPaid;
    private long totalFees;

    public Student(){
        id =0;
        name=null;
        course =null;
        feesPaid=0;
        totalFees =0;
    }
    public Student(int id, String name, String course,
                   long feesPaid, long totalFees) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.feesPaid = feesPaid;
        this.totalFees = totalFees;
    }
    public int getId() {
        return id;
    }
    public int setId(Scanner scanner) {
        int id;
        while (true) {
            try {
                System.out.println("Enter ID of student (positive number only):");
                String input = scanner.nextLine().trim();
                id = Integer.parseInt(input);

                if (id > 0) {
                    this.id = id;
                    return this.id;
                } else {
                    System.out.println("ID must be positive. Try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input! ID must be a number. Try again.");
            }
        }
    }



    public String getName() {
        return name;
    }

    public String setName(String name, Scanner scanner) {
        scanner.nextLine();
        while (name == null || !name.matches("[a-zA-Z ]+")) {
            System.out.println("Name must contain only letters. Enter name again:");
            name = scanner.next();
        }
        return this.name = name;
    }


    public String getCourse() {
        return course;
    }

    public String setCourse(String course, Scanner scanner) {
        scanner.nextLine();
        while (course == null || !course.matches("[a-zA-Z ]+")) {
            System.out.println("Course must contain only letters. Enter course again:");
            course = scanner.nextLine();
        }
        return this.course = course;
    }

    public long getFeesPaid() {
        return feesPaid;
    }

    public long setFeesPaid(long feesPaid, Scanner scanner) {
        while (feesPaid < 0 || feesPaid > this.totalFees) {
            System.out.println(
                    "Fees paid must be >= 0 and <= total fees (" + this.totalFees + "). Enter again:"
            );
            feesPaid = scanner.nextLong();
        }
        return this.feesPaid = feesPaid;
    }

    public long getTotalFees() {
        return totalFees;
    }

    public long setTotalFees(long totalFees, Scanner scanner) {
        while (totalFees <= 0 || totalFees < this.feesPaid) {
            System.out.println(
                    "Total fees must be positive and >= fees paid (" + this.feesPaid + "). Enter again:"
            );
            totalFees = scanner.nextLong();
        }
        return this.totalFees = totalFees;

    }

    public long payFees(long amount, Scanner scanner) {
        while (amount <= 0 || feesPaid + amount > totalFees) {
            if (amount <= 0) {
                System.out.println("Amount must be positive. Enter again:");
            } else {
                System.out.println("Payment exceeds total fees. Enter a smaller amount:");
            }
            amount = scanner.nextLong();
        }

        feesPaid += amount;
        return feesPaid;
    }

    public long getPendingFees(){
        return totalFees - feesPaid;
    }
    public void displayProfile(Student student){
        System.out.println("Id of student is :"+student.getId());
        System.out.println("name of student is :"+student.getName());
        System.out.println("course taken by  student is :"+student.getCourse());
        System.out.println("fees paid by student is :"+student.getFeesPaid());
        System.out.println("total fees of student is :"+student.getTotalFees());
    }
}
