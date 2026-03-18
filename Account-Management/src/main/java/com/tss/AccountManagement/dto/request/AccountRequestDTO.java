package com.tss.AccountManagement.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountRequestDTO {
    @NotNull
    private String accountName;
    @NotNull
    @PositiveOrZero
    private BigDecimal accountBalance;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Pattern(regexp = "^[0-9]{10}$")
    private String mobileNumber;
}
