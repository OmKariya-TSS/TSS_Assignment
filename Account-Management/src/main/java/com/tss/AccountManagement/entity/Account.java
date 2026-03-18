package com.tss.AccountManagement.entity;


import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="accounts")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;

    @Column(unique = true)
    private String accountNumber;
    @NonNull
    private String accountName;
    @NonNull
    private BigDecimal accountBalance;
    @NonNull
    private String email;
    private String mobileNumber;
    private LocalDateTime createdAt;
}
