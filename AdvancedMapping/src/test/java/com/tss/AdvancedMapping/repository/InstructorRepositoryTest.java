package com.tss.AdvancedMapping.repository;

import com.tss.AdvancedMapping.entity.Course;
import com.tss.AdvancedMapping.entity.Instructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class InstructorRepositoryTest {

    @Autowired
    private InstructorRepository instructorRepository;

    @Test
    void findByNameStartsWithCharacter() {
        Instructor instructor = new Instructor();
        instructor.setName("Om Kariya");
        Course course = new Course();
        course.setCourseName("Java");
        course.setDuration(2);
        course.setFees(5000.0);
        course.setInstructor(instructor);
        Course course2 = new Course();
        course.setCourseName("AI");
        course.setDuration(3);
        course.setFees(4000.0);
        course.setInstructor(instructor);
        instructor.setCourses(List.of(course, course2));
        instructorRepository.save(instructor);
        Instructor instructor1 = new Instructor();
        instructor1.setName("Om Kariya2");
        instructorRepository.save(instructor1);
        Pageable pageable = PageRequest.of(0, 2);
        Page<Instructor> page =
                instructorRepository.findByNameStartsWithCharacter('O', pageable);
        assertNotNull(page);
        assertEquals(2, page.getTotalElements());
    }


}