package com.tss.AdvancedMapping.mapper;

import com.tss.AdvancedMapping.dto.StudentRequestDTO;
import com.tss.AdvancedMapping.dto.StudentResponseDTO;
import com.tss.AdvancedMapping.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = AddressMapper.class)
public interface StudentMapper{
    Student toDto(StudentRequestDTO dto);
    StudentResponseDTO toResponseDTO(Student student);
}