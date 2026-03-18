package com.tss.AccountManagement.dto.request;


import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequestDTO {
    @PositiveOrZero
    private BigDecimal amount;
}
