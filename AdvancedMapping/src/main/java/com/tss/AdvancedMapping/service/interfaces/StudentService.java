package com.tss.AdvancedMapping.service.interfaces;

import com.tss.AdvancedMapping.dto.request.StudentRequestDTO;
import com.tss.AdvancedMapping.dto.response.CourseResponseDTO;
import com.tss.AdvancedMapping.dto.response.StudentResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {
    StudentResponseDTO saveStudent(StudentRequestDTO student);
    Page<StudentResponseDTO> findAllStudents(Pageable pageable);
    StudentResponseDTO findStudentById(long id);
    void deleteStudent(long id);
    Page<StudentResponseDTO> findByCity(String city,Pageable pageable);
    StudentResponseDTO findByRollNumber(Integer rollNumber);
    StudentResponseDTO assignCourseToStudent(Long studentId, Long courseId);
    StudentResponseDTO updateCourse(Long oldCourseId,Long StudentId,Long newCourseId);
}

