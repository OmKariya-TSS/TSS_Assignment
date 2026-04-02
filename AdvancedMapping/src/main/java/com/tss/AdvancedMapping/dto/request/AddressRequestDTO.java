package com.tss.AdvancedMapping.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressRequestDTO {
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private Integer pinCode;

}
