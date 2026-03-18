package com.tss.AdvancedMapping.mapper;


import com.tss.AdvancedMapping.dto.CourseRequestDTO;
import com.tss.AdvancedMapping.dto.CourseResponseDTO;
import com.tss.AdvancedMapping.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course toDTO(CourseRequestDTO courseRequestDTO);
    @Mapping(source = "instructor.instructorId", target = "instructorId")
    @Mapping(source = "instructor.name", target = "instructorName")
    CourseResponseDTO toResponseDTO(Course course);
    List<CourseResponseDTO> toResponseDTOList(List<Course> courses);
}
