package com.project.transactions.mapper;

import com.project.transactions.controller.data.request.AccountCreationRequest;
import com.project.transactions.controller.data.response.AccountResponse;
import com.project.transactions.domain.Account;

public abstract class AccountMapper {
  public static Account toDomain(AccountCreationRequest accountCreationRequest) {
    Account account = new Account();

    account.setDocumentNumber(accountCreationRequest.getDocumentNumber());

    return account;
  }

  public static AccountResponse toResponse(Account account) {
    AccountResponse accountCreationResponse = new AccountResponse();

    accountCreationResponse.setAccountId(account.getId());
    accountCreationResponse.setDocumentNumber(account.getDocumentNumber());

    return accountCreationResponse;
  }
}
