package com.project.transactions.controller.data.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionRequest {
    private Long accountId;
    private Integer operationTypeId;
    private BigDecimal amount;
}
