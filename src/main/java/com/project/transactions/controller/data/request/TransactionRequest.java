package com.project.transactions.controller.data.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionRequest {
    @NotNull(message = "'documentNumber' cannot be null.")
    private Long accountId;
    @NotNull(message = "'operationTypeId' cannot be null.")
    private int operationTypeId;
    // Adjust to be numbers only
    private BigDecimal amount;
}
