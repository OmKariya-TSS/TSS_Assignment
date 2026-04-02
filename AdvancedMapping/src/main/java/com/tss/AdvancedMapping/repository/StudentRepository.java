package com.tss.AdvancedMapping.repository;

import com.tss.AdvancedMapping.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Boolean existsByAddressCity(String city);
    Page<Student> findByAddressCity(String city, Pageable pageable);
    Optional<Student> findByRollNumber(Integer rollNumber);

}
