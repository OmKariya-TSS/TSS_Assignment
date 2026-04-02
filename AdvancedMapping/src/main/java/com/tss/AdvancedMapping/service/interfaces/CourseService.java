package com.tss.AdvancedMapping.service.interfaces;

import com.tss.AdvancedMapping.dto.request.CourseRequestDTO;
import com.tss.AdvancedMapping.dto.response.CourseResponseDTO;
import com.tss.AdvancedMapping.dto.response.InstructorResponseDTO;
import com.tss.AdvancedMapping.dto.response.StudentResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService {
    CourseResponseDTO createCourse(CourseRequestDTO courseRequestDTO);
    CourseResponseDTO findById(long id);
    Page<CourseResponseDTO> findAll(Pageable pageable);
    CourseResponseDTO assignInstructorToCourse(Long instructorId, Long courseId);
    InstructorResponseDTO assignCourseToInstructor(Long instructorId, Long courseId);
    List<CourseResponseDTO> findAllCourseByInstructor(Long instructorId);
    InstructorResponseDTO findInstructorByCourse(Long courseId);
    Integer findCountOfCourses(Long instructorId);
    Page<CourseResponseDTO> findCourseOfStudent(Long studentId, Pageable pageable);
    Page<StudentResponseDTO> findStudentOfCourse(Long courseId,Pageable pageable);


}
