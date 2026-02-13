package com.tss.Evaluation;

import com.tss.Evaluation.model.StudentRegistry;
import java.lang.reflect.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        System.out.println("enter number of students");

        int numOfStudents = scanner.nextInt();
        StudentRegistry reg = new StudentRegistry(numOfStudents);
        while(true) {
            System.out.println(reg.getStudentCounter());
            if(reg.getStudentCounter()==numOfStudents){
                break;
            }
            Student student = new Student();
            System.out.println("enter name");
            String name = scanner.next();
            try {
                student.setName(name);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("enter marks");
            for (int i = 0; i < student.marks.length; i++) {
                student.marks[i] = scanner.nextInt();
            }
            try {
                student.setMarks(student.marks);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("enter id :");
            int id = scanner.nextInt();
            student.setId(id);
            reg.addStudent(student);
        }
        for (Student regStudent : reg.getStudents()) {
            System.out.println(regStudent);
        }

    }
}
