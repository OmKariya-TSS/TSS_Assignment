package com.tss.AdvancedMapping.service;

import com.tss.AdvancedMapping.dto.CourseResponseDTO;
import com.tss.AdvancedMapping.dto.InstructorRequestDTO;
import com.tss.AdvancedMapping.dto.InstructorResponseDTO;
import com.tss.AdvancedMapping.entity.Instructor;
import com.tss.AdvancedMapping.exception.ResourceNotFoundException;
import com.tss.AdvancedMapping.mapper.InstuctorMapper;
import com.tss.AdvancedMapping.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final InstuctorMapper instructorMapper;
    @Override
    public InstructorResponseDTO createInstructor(InstructorRequestDTO instructorRequestDTO) {
        Instructor instructor = instructorMapper.toDTO(instructorRequestDTO);
        return instructorMapper.toResponseDTO(instructorRepository.save(instructor));
    }

    @Override
    public InstructorResponseDTO findById(long id) {
        return instructorMapper.toResponseDTO(instructorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("instructor",id)));
    }

    @Override
    public Page<InstructorResponseDTO> findAll(Pageable pageable) {
        return instructorRepository.findAll(pageable).map(instructorMapper::toResponseDTO);
    }


}
