package com.tss.AdvancedMapping.repository;

import com.tss.AdvancedMapping.entity.Course;
import com.tss.AdvancedMapping.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    @Query("select c from Course c where c.instructor.instructorId = :instructorId")
    List<Course> findCoursesByInstructorId(@Param("instructorId") Long instructorId);
    @Query("select count(c) from Course c where c.instructor.instructorId = :instructorId")
    Integer findCountOfCourses(@Param("instructorId") Long instructorId);
    @Query("SELECT c FROM Course c JOIN c.students s WHERE s.id = :studentId")
    Page<Course> findCoursesByStudentId(@Param("studentId") Long studentId, Pageable pageable);
    @Query("select c.students from Course c where c.courseId = :courseId")
    Page<Student> findStudentsByCourseId(@Param("courseId") Long courseId, Pageable pageable);
}
