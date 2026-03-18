package com.tss.repository;

import com.tss.config.DBConnection;
import com.tss.entity.Course;
import com.tss.entity.Student;

import java.sql.Connection;
import java.util.List;

public interface StudentCourseRepository {
    void assignCourse(int student_id,int course_id);
    List<Course> getCoursesOfStudent(int studentId);
    List<Student> getStudentsOfCourse(int courseId);
}
