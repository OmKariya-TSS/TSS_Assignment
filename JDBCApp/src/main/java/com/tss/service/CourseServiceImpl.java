package com.tss.service;

import com.tss.entity.Course;
import com.tss.repository.CourseRepository;
import com.tss.repository.CourseRepositoryImpl;

import java.util.List;

public class CourseServiceImpl implements CourseService{
    private CourseRepository courseRepo = new CourseRepositoryImpl();
    @Override
    public List<Course> getAllCourses() {
        return courseRepo.readCourses();
    }
    @Override
    public void addNewCourse(Course course) {
        courseRepo.addNewCourse(course);
    }
}
