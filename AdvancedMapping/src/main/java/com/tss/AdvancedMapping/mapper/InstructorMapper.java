package com.tss.AdvancedMapping.mapper;

import com.tss.AdvancedMapping.dto.request.InstructorRequestDTO;
import com.tss.AdvancedMapping.dto.response.InstructorResponseDTO;
import com.tss.AdvancedMapping.entity.Instructor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = CourseMapper.class)
public interface InstructorMapper {
    Instructor toDTO(InstructorRequestDTO instructorRequestDTO);
    InstructorResponseDTO toResponseDTO(Instructor instructor);
}
