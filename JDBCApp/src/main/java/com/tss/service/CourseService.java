package com.tss.service;


import com.tss.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    void addNewCourse(Course course);
}
