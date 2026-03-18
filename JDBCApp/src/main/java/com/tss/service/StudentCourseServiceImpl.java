package com.tss.service;

import com.tss.entity.Course;
import com.tss.entity.Student;
import com.tss.repository.StudentCourseRepository;
import com.tss.repository.StudentCourseRepositoryImpl;

import java.util.List;

public class StudentCourseServiceImpl implements StudentCourseService{
    private StudentCourseRepository studentCourseRepository = new StudentCourseRepositoryImpl();
    @Override
    public void assignCourse(int student_id, int course_id) {
        studentCourseRepository.assignCourse(student_id,course_id);
    }

    @Override
    public List<Course> getCourseOfStudent(int student_id) {
        return studentCourseRepository.getCoursesOfStudent(student_id);
    }

    @Override
    public List<Student> getStudentOfCourse(int course_id) {
        return studentCourseRepository.getStudentsOfCourse(course_id);
    }

}
