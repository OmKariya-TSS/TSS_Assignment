package com.tss.controller;

import com.tss.entity.Course;
import com.tss.entity.Student;
import com.tss.service.StudentCourseService;
import com.tss.service.StudentCourseServiceImpl;
import com.tss.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class StudentCourseController {
    private StudentCourseService studentCourseService = new StudentCourseServiceImpl();
    Scanner scanner = new Scanner(System.in);
    public void assignCourse(){
        System.out.println("enter student id :");
        int student_id = scanner.nextInt();
        System.out.println("enter course id :");
        int course_id = scanner.nextInt();
        studentCourseService.assignCourse(student_id,course_id);
    }
    public void getCoursesOfStudent(){
        System.out.println("enter student id :");
        int student_id = scanner.nextInt();
        List<Course> list = studentCourseService.getCourseOfStudent(student_id);
        for(Course course : list){
            System.out.println(course);
        }
    }
    public void getStudentOfCourses(){
        System.out.println("enter course id : ");
        int course_id = scanner.nextInt();
        List<Student> list = studentCourseService.getStudentOfCourse(course_id);
        for(Student student : list){
            System.out.println(student);
        }
    }
}
