package com.tss.AccountManagement.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class AccountResponseDTO {
    private Integer accountId;
    private String accountNumber;
    private String accountName;
    private BigDecimal accountBalance;
    private String email;
    private String mobileNumber;
    private LocalDateTime createdAt;
}
