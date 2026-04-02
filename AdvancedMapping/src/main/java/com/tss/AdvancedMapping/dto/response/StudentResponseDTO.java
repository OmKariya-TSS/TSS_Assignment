package com.tss.AdvancedMapping.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDTO {
    private Long id;
    private Integer rollNumber;
    private String name;
    private AddressResponseDTO address;
}
