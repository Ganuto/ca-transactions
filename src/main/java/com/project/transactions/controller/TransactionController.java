package com.project.transactions.controller;

import com.project.transactions.controller.data.request.TransactionRequest;
import com.project.transactions.controller.data.response.TransactionResponse;
import com.project.transactions.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

  private final TransactionService transactionService;

  @PostMapping
  public ResponseEntity<TransactionResponse> execute(
      @RequestBody @Valid TransactionRequest transactionRequest) {
    return ResponseEntity.ok(transactionService.execute(transactionRequest));
  }
}
