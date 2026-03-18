package com.tss.AccountManagement.controller;


import com.tss.AccountManagement.dto.request.TransactionRequestDTO;
import com.tss.AccountManagement.dto.request.UpdateDTO;
import com.tss.AccountManagement.dto.request.AccountRequestDTO;
import com.tss.AccountManagement.dto.response.AccountResponseDTO;
import com.tss.AccountManagement.exception.AccountException;
import com.tss.AccountManagement.exception.ErrorResponse;
import com.tss.AccountManagement.service.IAccountService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Data
@RestController
@RequestMapping("/accounts")
@Slf4j
public class AccountController {
    private final IAccountService accountService;
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Validated
    @PostMapping("/add")
    public ResponseEntity<AccountResponseDTO> createAccount(@Valid @RequestBody AccountRequestDTO accountRequestDTO){
        AccountResponseDTO created =  accountService.createAccount(accountRequestDTO);
        logger.info("Account created successfully");
        return ResponseEntity.status(201).body(created);
    }

    @Validated
    @GetMapping("/all")
    public ResponseEntity<Page<AccountResponseDTO>> findAllAccounts(
            @RequestParam(required = false) String accountNumber,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "3") Integer pageSize
    ){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<AccountResponseDTO> allAccounts = accountService.getAllAccounts(pageable);
        logger.info("All accounts found successfully");
        return ResponseEntity.status(200).body(allAccounts);
    }


    @Validated
    @GetMapping("/number")
    public ResponseEntity<AccountResponseDTO> findAccountByNumber(@RequestParam String accountNumber){
        AccountResponseDTO accountResponseDTO = accountService.getAccountByNumber(accountNumber);
        logger.info("Account found successfully");
        return ResponseEntity.status(200).body(accountResponseDTO);
    }


    @Validated
    @GetMapping("id")
    public ResponseEntity<AccountResponseDTO> getAccountById(@RequestParam Integer accountId){
        AccountResponseDTO accountResponseDTO = accountService.getAccountById(accountId);
        logger.info("Account found successfully");
        return ResponseEntity.status(200).body(accountResponseDTO);
    }

    @Validated
    @GetMapping("/name/{name}")
    public ResponseEntity<Page<AccountResponseDTO>> getAccountsByName(@PathVariable String name,
                                                                      @RequestParam(defaultValue = "0") Integer pageNumber,
                                                                      @RequestParam(defaultValue = "3")  Integer pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<AccountResponseDTO> allAccounts = accountService.getAccountsByName(name,pageable);
        logger.info("All accounts found successfully");
        return ResponseEntity.status(200).body(allAccounts);
    }


    @Validated
    @PutMapping("id/{id}")
    public ResponseEntity<AccountResponseDTO> updateAccount(@PathVariable Integer id,@Valid @RequestBody UpdateDTO updateDTO){
        AccountResponseDTO updated = accountService.updateMobileEmail(id,updateDTO);
        logger.info("Account updated successfully");
        return ResponseEntity.status(200).body(updated);
    }

    @Validated
    @DeleteMapping("/number/{number}")
    public String deleteAccountByNumber(@PathVariable String number){
        accountService.deleteAccountByNumber(number);
        logger.info("Account deleted successfully");
        return "deleted account with number "+number;
    }

    @Validated
    @PutMapping("/debit/{id}")
    public ResponseEntity<AccountResponseDTO> debitAccount(@PathVariable Integer id,@Valid @RequestBody TransactionRequestDTO transactionRequestDTO){
        logger.info("Debiting acccount in progress");
        return ResponseEntity.status(200).body(accountService.debitAccount(id,transactionRequestDTO));
    }

    @Validated
    @PutMapping("/credit/{id}")
    public ResponseEntity<AccountResponseDTO> creditAccount(@PathVariable Integer id,@Valid @RequestBody TransactionRequestDTO transactionRequestDTO){
        logger.info("Crediting acccount in progress");
        return ResponseEntity.status(200).body(accountService.creditAccount(id,transactionRequestDTO));
    }

    @Validated
    @PutMapping("/transfer")
    public ResponseEntity<List<AccountResponseDTO>> transferAmount(@RequestParam Integer toId, @RequestParam Integer fromId,@Valid @RequestBody TransactionRequestDTO transactionRequestDTO){
        logger.info("Transfer amount in progress");
        return ResponseEntity.status(200).body(accountService.transfer(toId,fromId,transactionRequestDTO));
    }
}
