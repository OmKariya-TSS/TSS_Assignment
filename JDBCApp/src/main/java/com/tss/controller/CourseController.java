package com.tss.controller;

import com.tss.entity.Course;
import com.tss.entity.Student;
import com.tss.service.CourseService;
import com.tss.service.CourseServiceImpl;
import com.tss.service.StudentService;
import com.tss.service.StudentServiceImpl;

import java.util.List;
import java.util.Scanner;

public class CourseController {
    private CourseService courseService = new CourseServiceImpl();
    public void readAllCourses(){
        List<Course> courses = courseService.getAllCourses();
        for(Course course: courses){
            System.out.println(course);
        }
    }
    public void addCourse(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter course id :");
        int course_id = scanner.nextInt();
        System.out.println("enter name :");
        String name =  scanner.next();
        System.out.println("enter fees :");
        double fees  = scanner.nextDouble();
        courseService.addNewCourse(new Course(course_id,name,fees));
    }
}
