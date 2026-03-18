package com.tss.AdvancedMapping.service;

import com.tss.AdvancedMapping.dto.CourseRequestDTO;
import com.tss.AdvancedMapping.dto.CourseResponseDTO;
import com.tss.AdvancedMapping.dto.InstructorResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {
    CourseResponseDTO createCourse(CourseRequestDTO courseRequestDTO);
    CourseResponseDTO findById(long id);
    Page<CourseResponseDTO> findAll(Pageable pageable);
    CourseResponseDTO assignInstructorToCourse(Long instructorId, Long courseId);
    InstructorResponseDTO assignCourseToInstructor(Long instructorId, Long courseId);
}
