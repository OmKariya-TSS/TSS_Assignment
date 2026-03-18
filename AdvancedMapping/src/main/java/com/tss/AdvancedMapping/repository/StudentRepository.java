package com.tss.AdvancedMapping.repository;

import com.tss.AdvancedMapping.dto.StudentResponseDTO;
import com.tss.AdvancedMapping.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface StudentRepository extends JpaRepository<Student,Long> {
    Boolean existsByAddressCity(String city);
    Page<Student> findByAddressCity(String city, Pageable pageable);
    Optional<Student> findByRollNumber(Integer rollNumber);
}
