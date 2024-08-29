package com.project.transactions.controller;

import com.project.transactions.controller.data.request.AccountCreationRequest;
import com.project.transactions.controller.data.response.AccountCreationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    public ResponseEntity<AccountCreationResponse> create(@RequestBody @Valid AccountCreationRequest accountCreationRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(new AccountCreationResponse());
    }
}
