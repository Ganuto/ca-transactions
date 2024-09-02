package com.project.transactions.controller.data.response;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionResponse {
  private Long transactionId;
  private Long accountId;
  private Integer operationTypeId;
  private BigDecimal amount;
}
