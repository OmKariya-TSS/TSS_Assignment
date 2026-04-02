package com.tss.CRUDProject.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequestDTO {
    @NotBlank
    private String productName;
    private String productDescription;
    @NotNull
    @Min(value = 0)
    private Double productPrice;
}