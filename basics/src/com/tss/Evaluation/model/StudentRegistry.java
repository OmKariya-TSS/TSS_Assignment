package com.tss.Evaluation.model;

import com.tss.Evaluation.Student;

public class StudentRegistry {
    private Student[] students;
    private int studentCounter=0;
    public StudentRegistry(int size){
        students = new Student[size];
    }
    public void addStudent(Student student){
        if(studentCounter==students.length-1){
            System.out.println("we can't create student");
            studentCounter++;
        }
        else{
            for(int i=0;i<students.length;i++){
                if(students[i]==null){
                    students[i] = student;
                    studentCounter++;
                    break;
                }
            }
        }
    }

    public int getStudentCounter() {
        return studentCounter;
    }

    public Student[] getStudents() {
        return students;
    }
}
