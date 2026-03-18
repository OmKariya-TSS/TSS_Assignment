package com.tss.hibernate_demo.DAO;

import com.tss.hibernate_demo.dto.response.StudentResponseDTO;
import com.tss.hibernate_demo.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Page<StudentResponseDTO> findByName(String name, Pageable pageable);
    Page<StudentResponseDTO> findByAge(Integer age, Pageable pageable);
}