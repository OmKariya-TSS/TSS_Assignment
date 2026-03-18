package com.tss.controller;


import com.tss.entity.Student;
import com.tss.service.StudentService;
import com.tss.service.StudentServiceImpl;

import java.util.List;
import java.util.Scanner;

public class StudentController {
    private StudentService studentService = new StudentServiceImpl();
    public void readAllStudents(){
        List<Student> students = studentService.getAllStudents();
        for(Student student : students){
            System.out.println(student);
        }
    }
    public void addStudent(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter student id :");
        int student_id = scanner.nextInt();
        System.out.println("enter roll number :");
        int roll_number =  scanner.nextInt();
        System.out.println("enter age :");
        int age  = scanner.nextInt();
        System.out.println("enter name :");
        String name = scanner.next();
        studentService.addNewStudent(new Student(student_id,roll_number,age,name));
    }
}
