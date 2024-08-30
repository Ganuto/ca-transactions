package com.project.transactions.service.impl;

import com.project.transactions.controller.data.request.TransactionRequest;
import com.project.transactions.controller.data.response.TransactionResponse;
import com.project.transactions.domain.Transaction;
import com.project.transactions.mapper.TransactionMapper;
import com.project.transactions.repository.TransactionRepository;
import com.project.transactions.service.AccountService;
import com.project.transactions.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    @Override
    public TransactionResponse execute(TransactionRequest transactionRequest) {
        validateAccountExistence(transactionRequest.getAccountId());
        Transaction transaction = TransactionMapper.toDomain(transactionRequest);
        Transaction persistedTransaction = transactionRepository.save(transaction);
        return TransactionMapper.toResponse(persistedTransaction);
    }

    private void validateAccountExistence(Long accountID){
        accountService.findById(accountID);
    }
}
