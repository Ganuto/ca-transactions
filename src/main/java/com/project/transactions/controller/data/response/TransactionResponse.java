package com.project.transactions.controller.data.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionResponse {
    private Long transactionId;
    private Long accountId;
    private Integer operationTypeId;
    private BigDecimal amount;
}
