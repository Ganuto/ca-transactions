package com.project.transactions.controller.data.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionRequest {
    @NotNull(message = "'documentNumber' cannot be null.")
    private Long accountId;
    @Min(value = 1, message = "'operationTypeId' cannot be less than 1")
    @Max(value = 4, message = "'operationTypeId' cannot be more than 4")
    private int operationTypeId;
    @NotNull(message = "'amount' cannot be null.")
    private BigDecimal amount;
}
