package com.project.transactions.mock;

import com.project.transactions.controller.data.request.AccountCreationRequest;
import com.project.transactions.controller.data.response.AccountResponse;

public class AccountMock {
    private static final String MOCK_DOCUMENT_NUMBER = "123456";

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

        accountResponse.setAccountId(1L);
        accountResponse.setDocumentNumber(MOCK_DOCUMENT_NUMBER);

        return accountResponse;
    }
}
