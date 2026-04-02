package com.tss.AdvancedMapping.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CourseRequestDTO {
    @NotNull
    private String courseName;
    @Positive
    private Integer duration;
    @Positive
    private Double fees;
    @NotNull
    private Long instructorId;
    @NotNull
    private String instructorName;
}