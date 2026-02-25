package com.tss.model;

public class Student {
    int id;
    String name;
    int totalMarks;
    StudentService s;
    public Student(int id, String name, int totalMarks) {
        this.id = id;
        this.name = name;
        this.totalMarks = totalMarks;
    }
    public void setStudentService(StudentService s){
        this.s=s;
    }
    public double calculatePercentage(){
        return (double) s.getTotalMarks()/s.getTotalStudents();
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

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }
}
