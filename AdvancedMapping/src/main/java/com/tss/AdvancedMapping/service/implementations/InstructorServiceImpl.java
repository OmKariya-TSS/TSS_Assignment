package com.tss.AdvancedMapping.service.implementations;

import com.tss.AdvancedMapping.dto.request.InstructorRequestDTO;
import com.tss.AdvancedMapping.dto.response.InstructorResponseDTO;
import com.tss.AdvancedMapping.entity.Instructor;
import com.tss.AdvancedMapping.exception.ResourceNotFoundException;
import com.tss.AdvancedMapping.mapper.InstructorMapper;
import com.tss.AdvancedMapping.repository.InstructorRepository;
import com.tss.AdvancedMapping.service.interfaces.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;
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

    @Override
    public Page<InstructorResponseDTO> findByStartCharacter(Character c,Pageable pageable) {
        return instructorRepository.findByNameStartsWithCharacter(c,pageable).map(instructorMapper::toResponseDTO);
    }
}
