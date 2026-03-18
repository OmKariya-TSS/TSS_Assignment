package com.tss.AccountManagement.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UpdateDTO {
    @NotNull
    @Pattern(regexp = "^[0-9]{10}$")
    private String mobileNumber;
    @Email
    private String email;
}
