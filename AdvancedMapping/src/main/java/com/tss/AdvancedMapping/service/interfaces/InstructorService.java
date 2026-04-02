package com.tss.AdvancedMapping.service.interfaces;

import com.tss.AdvancedMapping.dto.request.InstructorRequestDTO;
import com.tss.AdvancedMapping.dto.response.InstructorResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InstructorService {
    InstructorResponseDTO createInstructor(InstructorRequestDTO instructorRequestDTO);
    InstructorResponseDTO findById(long id);
    Page<InstructorResponseDTO> findAll(Pageable pageable);
    Page<InstructorResponseDTO> findByStartCharacter(Character c,Pageable pageable);
}
