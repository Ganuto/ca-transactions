package com.project.transactions.service.impl;

import com.project.transactions.controller.data.request.TransactionRequest;
import com.project.transactions.controller.data.response.TransactionResponse;
import com.project.transactions.domain.OperationType;
import com.project.transactions.domain.Transaction;
import com.project.transactions.domain.exception.BusinessException;
import com.project.transactions.mapper.TransactionMapper;
import com.project.transactions.repository.TransactionRepository;
import com.project.transactions.service.AccountService;
import com.project.transactions.service.TransactionService;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

  private final TransactionRepository transactionRepository;
  private final AccountService accountService;

  @Override
  public TransactionResponse execute(TransactionRequest transactionRequest) {
    Transaction transaction = TransactionMapper.toDomain(transactionRequest);
    validateAccountExistence(transaction.getAccountId());
    validateOperationAndAmount(transaction.getOperationType(), transaction.getAmount());
    Transaction persistedTransaction = transactionRepository.save(transaction);
    return TransactionMapper.toResponse(persistedTransaction);
  }

  private void validateAccountExistence(Long accountID) {
    accountService.findById(accountID);
  }

  private void validateOperationAndAmount(OperationType operationType, BigDecimal amount) {
    boolean isPositiveAmount = amount.signum() > 0;
    switch (operationType) {
      case PAYMENT -> {
        if (!isPositiveAmount) {
          throw new BusinessException(
              String.format(
                  "The operation [%s] with id [%s] cannot have negative amount [%s].",
                  operationType, operationType.getId(), amount));
        }
      }
      case INSTALLMENT_PURCHASE, PURCHASE, WITHDRAWAL -> {
        if (isPositiveAmount) {
          throw new BusinessException(
              String.format(
                  "The operation [%s] with id [%s] cannot have positive amount [%s].",
                  operationType, operationType.getId(), amount));
        }
      }
    }
  }
}
