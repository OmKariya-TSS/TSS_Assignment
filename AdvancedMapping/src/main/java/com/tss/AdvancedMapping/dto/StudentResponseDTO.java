package com.tss.AdvancedMapping.dto;

import com.tss.AdvancedMapping.entity.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
