package com.project.transactions;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.transactions.controller.data.request.TransactionRequest;
import com.project.transactions.controller.data.response.TransactionResponse;
import com.project.transactions.domain.Account;
import com.project.transactions.domain.OperationType;
import com.project.transactions.domain.Transaction;
import com.project.transactions.mock.AccountMock;
import com.project.transactions.mock.TransactionMock;
import com.project.transactions.repository.AccountRepository;
import com.project.transactions.repository.TransactionRepository;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TransactionIT extends TransactionsApplicationIT {

  @Autowired private AccountRepository accountRepository;
  @Autowired private TransactionRepository transactionRepository;
  @Autowired private ObjectMapper objectMapper;

  @Before
  public void init() {
    Account account = AccountMock.createAccount();
    accountRepository.save(account);
  }

  @Test
  public void executePurchaseSuccessfully() throws IOException {
    TransactionRequest transactionRequest =
        TransactionMock.createTransactionRequest(
            OperationType.PURCHASE, BigDecimal.valueOf(-10.99));

    String response =
        IntegrationRequests.post("/transactions", transactionRequest)
            .then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .response()
            .asString();

    TransactionResponse transactionResponse =
        objectMapper.readValue(response, TransactionResponse.class);

    Optional<Transaction> transactionOptional =
        transactionRepository.findById(transactionResponse.getTransactionId());

    assertTrue(transactionOptional.isPresent());
    Transaction transaction = transactionOptional.get();
    assertEquals(transactionResponse.getTransactionId(), transaction.getId());
    assertEquals(transactionResponse.getAccountId(), transaction.getAccountId());
    assertEquals(transactionResponse.getOperationTypeId(), transaction.getOperationType().getId());
    assertEquals(transactionResponse.getAmount(), transaction.getAmount());
  }

  @Test
  public void executeInstallmentPurchaseSuccessfully() throws IOException {
    TransactionRequest transactionRequest =
        TransactionMock.createTransactionRequest(
            OperationType.INSTALLMENT_PURCHASE, BigDecimal.valueOf(-10.23));

    String response =
        IntegrationRequests.post("/transactions", transactionRequest)
            .then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .response()
            .asString();

    TransactionResponse transactionResponse =
        objectMapper.readValue(response, TransactionResponse.class);

    Optional<Transaction> transactionOptional =
        transactionRepository.findById(transactionResponse.getTransactionId());

    assertTrue(transactionOptional.isPresent());
    Transaction transaction = transactionOptional.get();
    assertEquals(transactionResponse.getTransactionId(), transaction.getId());
    assertEquals(transactionResponse.getAccountId(), transaction.getAccountId());
    assertEquals(transactionResponse.getOperationTypeId(), transaction.getOperationType().getId());
    assertEquals(transactionResponse.getAmount(), transaction.getAmount());
  }

  @Test
  public void executeWithdrawSuccessfully() throws IOException {
    TransactionRequest transactionRequest =
        TransactionMock.createTransactionRequest(
            OperationType.INSTALLMENT_PURCHASE, BigDecimal.valueOf(-4.99));

    String response =
        IntegrationRequests.post("/transactions", transactionRequest)
            .then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .response()
            .asString();

    TransactionResponse transactionResponse =
        objectMapper.readValue(response, TransactionResponse.class);

    Optional<Transaction> transactionOptional =
        transactionRepository.findById(transactionResponse.getTransactionId());

    assertTrue(transactionOptional.isPresent());
    Transaction transaction = transactionOptional.get();
    assertEquals(transactionResponse.getTransactionId(), transaction.getId());
    assertEquals(transactionResponse.getAccountId(), transaction.getAccountId());
    assertEquals(transactionResponse.getOperationTypeId(), transaction.getOperationType().getId());
    assertEquals(transactionResponse.getAmount(), transaction.getAmount());
  }

  @Test
  public void executePaymentSuccessfully() throws IOException {
    TransactionRequest transactionRequest =
        TransactionMock.createTransactionRequest(OperationType.PAYMENT, BigDecimal.valueOf(32.22));

    String response =
        IntegrationRequests.post("/transactions", transactionRequest)
            .then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .response()
            .asString();

    TransactionResponse transactionResponse =
        objectMapper.readValue(response, TransactionResponse.class);

    Optional<Transaction> transactionOptional =
        transactionRepository.findById(transactionResponse.getTransactionId());

    assertTrue(transactionOptional.isPresent());
    Transaction transaction = transactionOptional.get();
    assertEquals(transactionResponse.getTransactionId(), transaction.getId());
    assertEquals(transactionResponse.getAccountId(), transaction.getAccountId());
    assertEquals(transactionResponse.getOperationTypeId(), transaction.getOperationType().getId());
    assertEquals(transactionResponse.getAmount(), transaction.getAmount());
  }

  @Test
  public void executePaymentWithNegativeAmountThenReturnBusinessException() throws IOException {
    TransactionRequest transactionRequest =
        TransactionMock.createTransactionRequest(OperationType.PAYMENT, BigDecimal.valueOf(-32.22));

    IntegrationRequests.post("/transactions", transactionRequest)
        .then()
        .assertThat()
        .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
  }

  @Test
  public void executePaymentWithNonExistentAccountIdThenReturnBusinessException()
      throws IOException {
    transactionRepository.deleteAll();

    TransactionRequest transactionRequest =
        TransactionMock.createTransactionRequest(OperationType.PAYMENT, BigDecimal.valueOf(100.22));
    Long nonExistentAccountId = 1337L;
    transactionRequest.setAccountId(nonExistentAccountId);

    IntegrationRequests.post("/transactions", transactionRequest)
        .then()
        .assertThat()
        .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
        .body(
            "error_message",
            equalTo(String.format("Account with id: [%s] not found.", nonExistentAccountId)));

    List<Transaction> transactionList =
        transactionRepository.findAllByAccountId(transactionRequest.getAccountId());

    assertTrue(transactionList.isEmpty());
  }
}
