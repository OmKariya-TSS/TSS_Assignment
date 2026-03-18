package com.tss.AdvancedMapping.dto;

import com.tss.AdvancedMapping.entity.Student;
import jakarta.persistence.*;
import jakarta.validation.Valid;
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
