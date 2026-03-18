package com.tss.AccountManagement.Mapper;


import com.tss.AccountManagement.dto.request.AccountRequestDTO;
import com.tss.AccountManagement.dto.response.AccountResponseDTO;
import com.tss.AccountManagement.entity.Account;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toAccountMapper(AccountRequestDTO accountRequestDTO);
    AccountResponseDTO toAccountResponseDTO(Account account);
}
