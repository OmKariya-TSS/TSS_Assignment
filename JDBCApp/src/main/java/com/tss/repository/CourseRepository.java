package com.tss.repository;

import com.tss.entity.Course;

import java.util.List;

public interface CourseRepository {
    List<Course> readCourses();
    void addNewCourse(Course course);
}
