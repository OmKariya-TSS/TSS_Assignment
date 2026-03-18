package com.tss.AdvancedMapping.repository;

import com.tss.AdvancedMapping.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
