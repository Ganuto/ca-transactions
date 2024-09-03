package com.project.transactions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.transactions.controller.data.request.AccountCreationRequest;
import com.project.transactions.controller.data.response.AccountResponse;
import com.project.transactions.domain.Account;
import com.project.transactions.mock.AccountMock;
import com.project.transactions.repository.AccountRepository;
import java.io.IOException;
import java.util.Optional;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountIT extends TransactionsApplicationIT {

  @Autowired private AccountRepository accountRepository;
  @Autowired private ObjectMapper objectMapper;

  @Test
  public void createSuccessfully() throws IOException {
    AccountCreationRequest accountCreationRequest = AccountMock.createAccountCreationRequest();

    String response =
        IntegrationRequests.post("/accounts", accountCreationRequest)
            .then()
            .assertThat()
            .statusCode(HttpStatus.CREATED.value())
            .extract()
            .response()
            .asString();

    AccountResponse accountResponse = objectMapper.readValue(response, AccountResponse.class);

    Optional<Account> accountOptional =
        accountRepository.findByDocumentNumber(accountCreationRequest.getDocumentNumber());
    assertTrue(accountOptional.isPresent());
    Account account = accountOptional.get();
    assertEquals(accountResponse.getAccountId(), account.getId());
    assertEquals(accountResponse.getDocumentNumber(), account.getDocumentNumber());
  }

  @Test
  public void getSuccessfully() throws JsonProcessingException {
    Long accountId = 1L;

    String response =
        IntegrationRequests.get(String.format("/accounts?accountId=%s", accountId))
            .then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .response()
            .asString();

    AccountResponse accountResponse = objectMapper.readValue(response, AccountResponse.class);

    Optional<Account> accountOptional = accountRepository.findById(accountResponse.getAccountId());

    assertTrue(accountOptional.isPresent());
    Account account = accountOptional.get();
    assertEquals(accountResponse.getAccountId(), account.getId());
    assertEquals(accountResponse.getDocumentNumber(), account.getDocumentNumber());
  }

  @Test
  public void createUnsuccessfully() throws IOException {
    AccountCreationRequest accountCreationRequest = new AccountCreationRequest();
    accountCreationRequest.setDocumentNumber("ABC");

    IntegrationRequests.post("/accounts", accountCreationRequest)
        .then()
        .assertThat()
        .statusCode(HttpStatus.BAD_REQUEST.value());

    Optional<Account> accountOptional =
        accountRepository.findByDocumentNumber(accountCreationRequest.getDocumentNumber());

    assertTrue(accountOptional.isEmpty());
  }

  @Test
  public void getUnsuccessfully() {
    Long accountId = -1L;

    IntegrationRequests.get(String.format("/accounts?accountId=%s", accountId))
        .then()
        .assertThat()
        .statusCode(HttpStatus.BAD_REQUEST.value());

    Optional<Account> accountOptional = accountRepository.findById(accountId);

    assertTrue(accountOptional.isEmpty());
  }
}
