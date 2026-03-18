package com.tss.AdvancedMapping.dto;


import com.tss.AdvancedMapping.entity.Instructor;
import lombok.Data;

@Data
public class CourseResponseDTO {
    private long courseId;
    private String courseName;
    private Integer duration;
    private Double fees;
    private Long instructorId;
    private String instructorName;
}