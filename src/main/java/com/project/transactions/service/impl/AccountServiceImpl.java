package com.project.transactions.service.impl;

import com.project.transactions.controller.data.request.AccountCreationRequest;
import com.project.transactions.controller.data.response.AccountCreationResponse;
import com.project.transactions.controller.data.response.AccountResponse;
import com.project.transactions.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    @Override
    public ResponseEntity<AccountCreationResponse> create(AccountCreationRequest accountCreationRequest) {
        return null;
    }

    @Override
    public ResponseEntity<AccountResponse> get(Long accountId) {
        return null;
    }
}
