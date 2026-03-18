package com.tss.AdvancedMapping.service;

import com.tss.AdvancedMapping.dto.InstructorRequestDTO;
import com.tss.AdvancedMapping.dto.InstructorResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InstructorService {
    InstructorResponseDTO createInstructor(InstructorRequestDTO instructorRequestDTO);
    InstructorResponseDTO findById(long id);
    Page<InstructorResponseDTO> findAll(Pageable pageable);
}
