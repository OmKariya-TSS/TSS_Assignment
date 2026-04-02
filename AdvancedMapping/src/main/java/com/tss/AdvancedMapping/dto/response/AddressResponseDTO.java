package com.tss.AdvancedMapping.dto.response;

import lombok.AllArgsConstructor;
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
