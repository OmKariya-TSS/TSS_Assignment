package com.tss.AdvancedMapping.dto;

import com.tss.AdvancedMapping.entity.Address;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

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
