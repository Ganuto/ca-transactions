package com.project.transactions.mock;

import com.project.transactions.controller.data.request.AccountCreationRequest;
import com.project.transactions.controller.data.response.AccountResponse;
import com.project.transactions.domain.Account;

public class AccountMock {
  private static final String MOCK_DOCUMENT_NUMBER = "123456";
  private static final Long MOCK_ACCOUNT_ID = 1L;

  public static AccountCreationRequest createAccountCreationRequest() {
    AccountCreationRequest accountCreationRequest = new AccountCreationRequest();

    accountCreationRequest.setDocumentNumber(MOCK_DOCUMENT_NUMBER);

    return accountCreationRequest;
  }

  public static AccountCreationRequest createWrongAccountCreationRequest() {
    AccountCreationRequest accountCreationRequest = new AccountCreationRequest();

    accountCreationRequest.setDocumentNumber("NOT_A_NUMBER");

    return accountCreationRequest;
  }

  public static AccountResponse createAccountResponse() {
    AccountResponse accountResponse = new AccountResponse();

    accountResponse.setAccountId(MOCK_ACCOUNT_ID);
    accountResponse.setDocumentNumber(MOCK_DOCUMENT_NUMBER);

    return accountResponse;
  }

  public static Account createAccount() {
    Account account = new Account();

    account.setId(MOCK_ACCOUNT_ID);
    account.setDocumentNumber(MOCK_DOCUMENT_NUMBER);

    return account;
  }
}
