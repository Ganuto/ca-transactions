package com.project.transactions;

import static org.junit.jupiter.api.Assertions.*;

import com.project.transactions.controller.data.request.TransactionRequest;
import com.project.transactions.domain.Account;
import com.project.transactions.domain.OperationType;
import com.project.transactions.domain.Transaction;
import com.project.transactions.mock.AccountMock;
import com.project.transactions.mock.TransactionMock;
import com.project.transactions.repository.AccountRepository;
import com.project.transactions.repository.TransactionRepository;
import java.io.IOException;
import java.math.BigDecimal;
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

    IntegrationRequests.post("/transactions", transactionRequest)
        .then()
        .assertThat()
        .statusCode(HttpStatus.OK.value());

    Optional<Transaction> transactionOptional = transactionRepository.findById(1L);

    assertTrue(transactionOptional.isPresent());
    Transaction transaction = transactionOptional.get();
    assertNotNull(transaction.getId());
    assertEquals(transactionRequest.getAccountId(), transaction.getAccountId());
    assertEquals(transactionRequest.getOperationTypeId(), transaction.getOperationType().getId());
    assertEquals(transactionRequest.getAmount(), transaction.getAmount());
  }
}
