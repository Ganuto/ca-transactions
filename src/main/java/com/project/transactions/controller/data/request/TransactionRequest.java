package com.project.transactions.controller.data.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest {
  @NotNull(message = "'account_id' cannot be null.")
  @Positive(message = "'account_id' cannot be negative.")
  private Long accountId;

  @Min(value = 1, message = "'operation_type_id' cannot be less than 1.")
  @Max(value = 4, message = "'operation_type_id' cannot be more than 4.")
  private int operationTypeId;

  @NotNull(message = "'amount' cannot be null.")
  private BigDecimal amount;
}
