package com.tss.AdvancedMapping.dto;


import com.tss.AdvancedMapping.entity.Course;
import lombok.Data;

import java.util.List;

@Data
public class InstructorResponseDTO {
    private Long instructorId;
    private String name;
    private String qualification;
    private List<CourseResponseDTO> courses;
}