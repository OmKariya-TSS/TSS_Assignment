package com.tss.AccountManagement.service;

import com.tss.AccountManagement.dto.request.UpdateDTO;
import com.tss.AccountManagement.dto.request.AccountRequestDTO;
import com.tss.AccountManagement.dto.request.TransactionRequestDTO;
import com.tss.AccountManagement.dto.response.AccountResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;


public interface IAccountService {
    Page<AccountResponseDTO>  getAllAccounts(Pageable pageable);
    AccountResponseDTO getAccountById(Integer id);
    Page<AccountResponseDTO> getAccountsByName(String account_name, Pageable pageable);
    AccountResponseDTO getAccountByNumber(String account_number);
    AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO);
    AccountResponseDTO updateMobileEmail(Integer id, UpdateDTO updateDTO);
    void deleteAccountByNumber(String Account_number);
    AccountResponseDTO debitAccount(Integer id, TransactionRequestDTO transactionRequestDTO);
    AccountResponseDTO creditAccount(Integer id, TransactionRequestDTO transactionRequestDTO);
    List<AccountResponseDTO> transfer(Integer toId,Integer fromId, TransactionRequestDTO transactionRequestDTO);

}
