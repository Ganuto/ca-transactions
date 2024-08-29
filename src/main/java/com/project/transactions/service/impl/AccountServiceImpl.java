package com.project.transactions.service.impl;

import com.project.transactions.controller.data.request.AccountCreationRequest;
import com.project.transactions.controller.data.response.AccountCreationResponse;
import com.project.transactions.controller.data.response.AccountResponse;
import com.project.transactions.domain.Account;
import com.project.transactions.mapper.AccountMapper;
import com.project.transactions.repository.AccountRepository;
import com.project.transactions.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountCreationResponse create(AccountCreationRequest accountCreationRequest) {
        Account account = accountRepository.save(AccountMapper.toDomain(accountCreationRequest));
        return AccountMapper.toResponse(account);
    }

    @Override
    public AccountResponse get(Long accountId) {
        return null;
    }
}
