package com.tss.AdvancedMapping.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentRequestDTO {
    @Positive
    private Integer rollNumber;
    @NotNull
    private String name;
    @Valid
    private AddressRequestDTO address;
}
