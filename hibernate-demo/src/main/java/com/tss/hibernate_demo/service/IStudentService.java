package com.tss.hibernate_demo.service;


import com.tss.hibernate_demo.dto.page.StudentResponsePageDTO;
import com.tss.hibernate_demo.dto.request.StudentRequestDTO;
import com.tss.hibernate_demo.dto.response.StudentResponseDTO;
import com.tss.hibernate_demo.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    StudentResponseDTO saveStudent(StudentRequestDTO student);
    Page<StudentResponseDTO> getAllStudents(Integer pageSize, Integer pageNumber);
    Optional<StudentResponseDTO> getStudentById(@PathVariable Integer id);
    StudentResponseDTO updateStudent(Integer id, Student student);
    void deleteStudent(Integer id);
    Page<StudentResponseDTO> getStudentsByName(String name,Integer pageNumber,Integer pageSize);
    Page<StudentResponseDTO> findByAge(Integer age,Integer pageNumber,Integer PageSize);
    StudentResponsePageDTO getAllStudentsPage(Integer pageNumber,Integer pageSize);
}