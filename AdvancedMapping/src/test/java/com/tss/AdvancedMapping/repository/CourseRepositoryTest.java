package com.tss.AdvancedMapping.repository;

import com.tss.AdvancedMapping.entity.Course;
import com.tss.AdvancedMapping.entity.Instructor;
import com.tss.AdvancedMapping.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CourseRepositoryTest {

    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Test
    void findCoursesByInstructorId() {

        Instructor instructor = new Instructor();
        instructor.setName("Om Kariya");

        Course course = new Course();
        course.setCourseName("Java");
        course.setDuration(2);
        course.setFees(5000.0);
        course.setInstructor(instructor);

        Course course2 = new Course();
        course2.setCourseName("AI");
        course2.setDuration(3);
        course2.setFees(4000.0);
        course2.setInstructor(instructor);
        instructor.setCourses(List.of(course, course2));

        instructorRepository.save(instructor);

        Long instructorId = instructor.getInstructorId();

        List<Course> list = courseRepository.findCoursesByInstructorId(instructorId);

        assertNotNull(list);
        assertEquals(2, list.size());
    }
    @Test
    void findCountOfCourses() {

        Instructor instructor = new Instructor();
        instructor.setName("Om Kariya");

        Course course = new Course();
        course.setCourseName("Java");
        course.setDuration(2);
        course.setFees(5000.0);
        course.setInstructor(instructor);

        Course course2 = new Course();
        course2.setCourseName("AI");
        course2.setDuration(3);
        course2.setFees(4000.0);
        course2.setInstructor(instructor);
        instructor.setCourses(List.of(course, course2));

        instructorRepository.save(instructor);

        Long instructorId = instructor.getInstructorId();
        Integer count =  courseRepository.findCountOfCourses(instructorId);
        assertNotNull(count);
        assertEquals(2, count);
    }

    @Test
    void findCoursesByStudentId() {

        Instructor instructor = new Instructor();
        instructor.setName("Om Kariya");

        Course course = new Course();
        course.setCourseName("Java");
        course.setDuration(2);
        course.setFees(5000.0);
        course.setInstructor(instructor);

        Course course2 = new Course();
        course2.setCourseName("AI");
        course2.setDuration(3);
        course2.setFees(4000.0);
        course2.setInstructor(instructor);
        instructor.setCourses(List.of(course, course2));

        instructorRepository.save(instructor);
        Student student = new Student();
        student.setName("new stud");
        student.setAddress(null);
        student.setCourses(List.of(course,course2));
        course.setStudents(List.of(student));
        course2.setStudents(List.of(student));

        courseRepository.save(course);
        courseRepository.save(course2);

        studentRepository.save(student);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Course> page = courseRepository.findCoursesByStudentId(student.getId(),pageable);
        assertNotNull(page);
        assertEquals(2, page.getTotalElements());

    }

}