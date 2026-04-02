package com.tss.AdvancedMapping.dto.response;


import lombok.Data;

import java.util.List;

@Data
public class InstructorResponseDTO {
    private Long instructorId;
    private String name;
    private String qualification;
    private List<CourseResponseDTO> courses;
}