package com.project.transactions.controller.data.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionRequest {
    @NotNull(message = "'documentNumber' cannot be null.")
    private Long accountId;
    @NotNull(message = "'operationTypeId' cannot be null.")
    private Integer operationTypeId;
    @Positive(message = "'amount' cannot be null.")
    private BigDecimal amount;
}
