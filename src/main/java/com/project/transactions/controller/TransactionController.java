package com.project.transactions.controller;

import com.project.transactions.controller.data.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    public ResponseEntity<TransactionResponse> execute(){
        return ResponseEntity.ok(new TransactionResponse());
    }
}
