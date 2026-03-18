package com.tss.hibernate_demo.dto.response;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDTO {

    private Integer student_id;
    private String name;
    private Integer age;
}