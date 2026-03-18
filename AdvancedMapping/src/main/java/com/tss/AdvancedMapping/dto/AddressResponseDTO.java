package com.tss.AdvancedMapping.dto;

import com.tss.AdvancedMapping.entity.Student;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDTO {
    private Long addressId;
    private String city;
    private String state;
    private Integer pinCode;
}
