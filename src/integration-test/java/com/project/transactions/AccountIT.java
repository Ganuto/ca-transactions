package com.project.transactions;

import com.project.transactions.controller.data.request.AccountCreationRequest;
import com.project.transactions.domain.Account;
import com.project.transactions.mock.AccountMock;
import com.project.transactions.repository.AccountRepository;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
public class AccountIT extends TransactionsApplicationIT {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @Order(1)
    public void createAccountSuccessfully() throws IOException {
        AccountCreationRequest accountCreationRequest = AccountMock.createAccountCreationRequest();

        IntegrationRequests.post("/accounts", accountCreationRequest)
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

        Account account = accountRepository.findByDocumentNumber(accountCreationRequest.getDocumentNumber()).get();
    }

    @Test
    @Order(2)
    public void getAccountSuccessfully() {
        Long accountId = 1L;

        IntegrationRequests.get(String.format("/accounts?accountId=%s", accountId))
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());

        Account account = accountRepository.findById(accountId).get();

    }
}
