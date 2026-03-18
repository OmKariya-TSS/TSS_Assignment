package com.tss.service;

import com.tss.entity.Course;
import com.tss.entity.Student;

import java.util.List;

public interface StudentCourseService {
    void assignCourse(int student_id,int course_id);
    List<Course> getCourseOfStudent(int student_id);
    List<Student> getStudentOfCourse(int course_id);
}
