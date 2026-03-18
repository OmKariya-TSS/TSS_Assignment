package com.tss.AdvancedMapping.service;

import com.tss.AdvancedMapping.dto.StudentRequestDTO;
import com.tss.AdvancedMapping.dto.StudentResponseDTO;
import com.tss.AdvancedMapping.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    StudentResponseDTO saveStudent(StudentRequestDTO student);
    Page<StudentResponseDTO> findAllStudents(Pageable pageable);
    StudentResponseDTO findStudentById(long id);
    void deleteStudent(long id);
    Page<StudentResponseDTO> findByCity(String city,Pageable pageable);
    StudentResponseDTO findByRollNumber(Integer rollNumber);
}

