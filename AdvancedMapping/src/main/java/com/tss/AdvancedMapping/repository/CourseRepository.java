package com.tss.AdvancedMapping.repository;

import com.tss.AdvancedMapping.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
