package com.project.transactions.service.impl;

import com.project.transactions.controller.data.request.AccountCreationRequest;
import com.project.transactions.controller.data.response.AccountResponse;
import com.project.transactions.domain.Account;
import com.project.transactions.domain.exception.BusinessException;
import com.project.transactions.mapper.AccountMapper;
import com.project.transactions.repository.AccountRepository;
import com.project.transactions.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountResponse create(AccountCreationRequest accountCreationRequest) {
        try{
            Account account = accountRepository.save(AccountMapper.toDomain(accountCreationRequest));
            return AccountMapper.toResponse(account);
        } catch (DataAccessException ex){
            throw new BusinessException(String.format("DocumentNumber [%s] already exists.", accountCreationRequest.getDocumentNumber()) );
        }
    }

    @Override
    public AccountResponse findById(Long accountId) {
        Account account = Optional.of(accountRepository.findById(accountId))
                .get().orElseThrow(() -> new BusinessException(String.format("Account with id: [%s] not found.", accountId)));
        return AccountMapper.toResponse(account);
    }
}
