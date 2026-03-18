package com.tss.AccountManagement.service;

import com.tss.AccountManagement.Mapper.AccountMapper;
import com.tss.AccountManagement.dao.AccountRepository;
import com.tss.AccountManagement.dto.request.TransactionRequestDTO;
import com.tss.AccountManagement.dto.request.UpdateDTO;
import com.tss.AccountManagement.dto.request.AccountRequestDTO;
import com.tss.AccountManagement.dto.response.AccountResponseDTO;
import com.tss.AccountManagement.entity.Account;
import com.tss.AccountManagement.exception.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
@RequiredArgsConstructor
@Service
@Slf4j
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Override
    public Page<AccountResponseDTO> getAllAccounts(Pageable pageable) {
        logger.info("Getting all accounts");
        return accountRepository.findAll(pageable).map(accountMapper::toAccountResponseDTO);
    }

    @Override
    public AccountResponseDTO getAccountById(Integer id) {
        logger.info("Getting account by id: {}", id);
        return accountMapper.toAccountResponseDTO(
                accountRepository
                .findById(id)
                .orElseThrow(()->{return new ResourceNotFoundException("Account",id);})
        );
    }

    @Override
    public Page<AccountResponseDTO> getAccountsByName(String account_name, Pageable pageable) {
        Page<Account> accountResponseDTOPage = accountRepository.findByAccountName(account_name, pageable);
        logger.info("Getting account by name: {}", account_name);
        return accountResponseDTOPage.map(accountMapper::toAccountResponseDTO);
    }

    @Override
    public AccountResponseDTO getAccountByNumber(String account_number) {
        Account account = accountRepository.findByAccountNumber(account_number);
        logger.info("Getting account by number: {}", account_number);
        return accountMapper.toAccountResponseDTO(account);
    }

    @Override
    public AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO) {
        Account account = new Account();
        account.setAccountBalance(accountRequestDTO.getAccountBalance());
        account.setAccountName(accountRequestDTO.getAccountName());
        account.setCreatedAt(LocalDateTime.now());
        account.setEmail(accountRequestDTO.getEmail());
        account.setMobileNumber(accountRequestDTO.getMobileNumber());
        account.setAccountNumber(generateAccountNumber());
        logger.info("Creating account with account number: {} " , account.getAccountNumber());
        return accountMapper.toAccountResponseDTO(accountRepository.save(account));
    }
    public String generateAccountNumber() {
        String account_number;
        do{
            String bankCode = "TSS";
            int year = LocalDateTime.now().getYear();
            int random = 100000 + new Random().nextInt(900000);
            account_number = bankCode + year + random;
        }while(accountRepository.existsByAccountNumber(account_number));
        logger.info("Generated account number: {}", account_number);
        return account_number;
    }

    @Override
    public AccountResponseDTO updateMobileEmail(Integer id, UpdateDTO  updateDTO) {
        Account account = accountRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Account ",id));
        account.setEmail(updateDTO.getEmail());
        account.setMobileNumber(updateDTO.getMobileNumber());
        logger.info("Updating account with account number: {} " , account.getAccountNumber());
        return accountMapper.toAccountResponseDTO(accountRepository.save(account));
    }

    @Transactional
    @Override
    public void deleteAccountByNumber(String account_number) {
        if(!accountRepository.existsByAccountNumber(account_number)){
            throw new ResourceNotFoundException("Account not found with number : ",account_number);
        }
        logger.info("Deleting account with account number: {} " , account_number);
        accountRepository.deleteByAccountNumber(account_number);
    }

    @Override
    public AccountResponseDTO debitAccount(Integer id, TransactionRequestDTO transactionRequestDTO) {
        Account account = accountRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Account ",id));
        if(account.getAccountBalance().compareTo(transactionRequestDTO.getAmount()) < 0){
            throw new BusinessRuleException("Insufficient balance");
        }
        account.setAccountBalance(account.getAccountBalance().subtract(transactionRequestDTO.getAmount()));
        return accountMapper.toAccountResponseDTO(accountRepository.save(account));
    }

    @Override
    public AccountResponseDTO creditAccount(Integer id, TransactionRequestDTO transactionRequestDTO) {
        Account account = accountRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Account not found with id ",id));
        account.setAccountBalance(account.getAccountBalance().add(transactionRequestDTO.getAmount()));
        return accountMapper.toAccountResponseDTO(accountRepository.save(account));
    }

    @Override
    public List<AccountResponseDTO> transfer(Integer toId, Integer fromId, TransactionRequestDTO transactionRequestDTO) {
        Account toAcc = accountRepository.findById(toId).orElseThrow(()->new ResourceNotFoundException("Account not found with id ",toId));
        Account fromAcc = accountRepository.findById(fromId).orElseThrow(()->new ResourceNotFoundException("Account not found with id ",fromId));
        debitAccount(fromId, transactionRequestDTO);
        creditAccount(toId, transactionRequestDTO);
        List<AccountResponseDTO> list = new ArrayList<>();
        list.add(accountMapper.toAccountResponseDTO(toAcc));
        list.add(accountMapper.toAccountResponseDTO(fromAcc));
        logger.info("Transferring money from {} to {}",fromAcc.getAccountName(),toAcc.getAccountId());
        return list;
    }


}
