package com.tss.AdvancedMapping.mapper;

import com.tss.AdvancedMapping.dto.CourseRequestDTO;
import com.tss.AdvancedMapping.dto.CourseResponseDTO;
import com.tss.AdvancedMapping.dto.InstructorRequestDTO;
import com.tss.AdvancedMapping.dto.InstructorResponseDTO;
import com.tss.AdvancedMapping.entity.Course;
import com.tss.AdvancedMapping.entity.Instructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = CourseMapper.class)
public interface InstuctorMapper {
    Instructor toDTO(InstructorRequestDTO instructorRequestDTO);
    InstructorResponseDTO toResponseDTO(Instructor instructor);
}
