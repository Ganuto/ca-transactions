package com.project.transactions.controller;

import com.project.transactions.controller.data.request.AccountCreationRequest;
import com.project.transactions.controller.data.response.AccountCreationResponse;
import com.project.transactions.controller.data.response.AccountResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    @PostMapping
    public ResponseEntity<AccountCreationResponse> create(@RequestBody @Valid AccountCreationRequest accountCreationRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(new AccountCreationResponse());
    }

    @GetMapping
    public ResponseEntity<AccountResponse> get(@RequestParam Long accountId){
        return ResponseEntity.ok(new AccountResponse());
    }
}
