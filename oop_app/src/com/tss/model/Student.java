package com.tss.model;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Student {
    private int id ;
    private String name;
    private String course;
    private long feesPaid;
    private long totalFees;

    public Student(){

    }
    public Student(int id, String name, String course,
                   long feesPaid, long totalFees) {
        setId(id);
        setName(name);
        setCourse(course);
        setTotalFees(totalFees);
        setFeesPaid(feesPaid);
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number.");
        }
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {
        if (!name.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Name must contain only letters and spaces.");
        }
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) throws IllegalArgumentException {
        if (course == null || !course.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Course must contain only letters and spaces.");
        }
        this.course = course;
    }


    public void setTotalFees(long totalFees) throws IllegalArgumentException {
        if (totalFees <= 0) {
            throw new IllegalArgumentException("Total fees must be positive.");
        }
        this.totalFees = totalFees;
    }
    public long getTotalFees() {
        return totalFees;
    }


    public void setFeesPaid(long feesPaid) throws IllegalArgumentException {
        if (feesPaid < 0 || feesPaid > totalFees) {
            throw new IllegalArgumentException(
                    "Fees paid must be >= 0 and <= total fees (" + this.totalFees + ")."
            );
        }
        this.feesPaid = feesPaid;
    }
    public long getFeesPaid() {
        return feesPaid;
    }


    public void payFees(long amount) throws Exception {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        if(amount>totalFees){
            throw new IllegalArgumentException("please enter valid amount");
        }
        if (feesPaid + amount >totalFees) {
            throw new IllegalArgumentException(
                    "Payment exceeds total fees. Current fees paid: " + feesPaid
                            + ", total fees: " + totalFees
            );
        }
        feesPaid += amount;
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
