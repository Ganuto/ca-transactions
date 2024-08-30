package com.project.transactions.service;

import com.project.transactions.controller.data.request.TransactionRequest;
import com.project.transactions.controller.data.response.TransactionResponse;

public interface TransactionService {
    TransactionResponse execute(TransactionRequest transactionRequest);
}
