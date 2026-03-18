package com.tss.AccountManagement.dao;

import com.tss.AccountManagement.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Page<Account> findByAccountName(String accountName, Pageable pageable);
    Account findByAccountNumber(String accountNumber);
    Boolean existsByAccountNumber(String accountNumber);
    void deleteByAccountNumber(String accountNumber);
}
