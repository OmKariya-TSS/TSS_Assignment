package com.tss.AdvancedMapping.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class InstructorRequestDTO {
    @NotNull
    private String name;
    private String qualification;
    private List<CourseRequestDTO> courses;
}