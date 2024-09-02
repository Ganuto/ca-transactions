package com.project.transactions.mapper;

import com.project.transactions.controller.data.request.TransactionRequest;
import com.project.transactions.controller.data.response.TransactionResponse;
import com.project.transactions.domain.OperationType;
import com.project.transactions.domain.Transaction;

public abstract class TransactionMapper {

  public static Transaction toDomain(TransactionRequest transactionRequest) {
    Transaction transaction = new Transaction();

    transaction.setAccountId(transactionRequest.getAccountId());
    transaction.setOperationType(OperationType.getById(transactionRequest.getOperationTypeId()));
    transaction.setAmount(transactionRequest.getAmount());

    return transaction;
  }

  public static TransactionResponse toResponse(Transaction transaction) {
    TransactionResponse transactionResponse = new TransactionResponse();

    transactionResponse.setTransactionId(transaction.getId());
    transactionResponse.setAccountId(transaction.getAccountId());
    transactionResponse.setOperationTypeId(transaction.getOperationType().getId());
    transactionResponse.setAmount(transaction.getAmount());

    return transactionResponse;
  }
}
