package com.tss.model;

public class Student {
    private int id ;
    private String name;
    private String course;
    private long feesPaid;
    private long totalFees;

    public Student(int id, String name, String course, long feesPaid, long totalFees) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.feesPaid = feesPaid;
        this.totalFees = totalFees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public long getFeesPaid() {
        return feesPaid;
    }

    public void setFeesPaid(long feesPaid) {
        this.feesPaid = feesPaid;
    }

    public long getTotalFees() {
        return totalFees;
    }

    public void setTotalFees(long totalFees) {
        this.totalFees = totalFees;
    }
    public long payFees(long amount){
        return feesPaid+=amount;
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
