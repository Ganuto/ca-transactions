package com.project.transactions.mock;

import com.project.transactions.controller.data.request.TransactionRequest;
import com.project.transactions.controller.data.response.TransactionResponse;
import com.project.transactions.domain.OperationType;
import com.project.transactions.domain.Transaction;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionMock {
  private static final Long MOCK_TRANSACTION_ID = 1L;
  private static final Long MOCK_ACCOUNT_ID = 1L;

  public static TransactionRequest createTransactionRequest(
      OperationType operationType, BigDecimal amount) {
    TransactionRequest transactionRequest = new TransactionRequest();

    transactionRequest.setAccountId(MOCK_ACCOUNT_ID);
    transactionRequest.setOperationTypeId(operationType.getId());
    transactionRequest.setAmount(amount);

    return transactionRequest;
  }

  public static TransactionResponse createTransactionResponse(
      OperationType operationType, BigDecimal amount) {
    TransactionResponse transactionResponse = new TransactionResponse();

    transactionResponse.setAccountId(MOCK_ACCOUNT_ID);
    transactionResponse.setOperationTypeId(operationType.getId());
    transactionResponse.setTransactionId(MOCK_TRANSACTION_ID);
    transactionResponse.setAmount(amount);

    return transactionResponse;
  }

  public static Transaction createTransaction(OperationType operationType, BigDecimal amount) {
    Transaction transaction = new Transaction();

    transaction.setId(MOCK_TRANSACTION_ID);
    transaction.setAccountId(MOCK_ACCOUNT_ID);
    transaction.setOperationType(operationType);
    transaction.setAmount(amount);
    transaction.setEventDate(LocalDateTime.of(2024, 8, 19, 0, 0));

    return transaction;
  }
}
