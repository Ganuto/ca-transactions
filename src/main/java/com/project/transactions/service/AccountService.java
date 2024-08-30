package com.project.transactions.service;

import com.project.transactions.controller.data.request.AccountCreationRequest;
import com.project.transactions.controller.data.response.AccountResponse;

public interface AccountService {

    AccountResponse create(AccountCreationRequest accountCreationRequest);
    AccountResponse findById(Long accountId);
}
