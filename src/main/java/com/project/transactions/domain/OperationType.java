package com.project.transactions.domain;

import com.project.transactions.domain.exception.BusinessException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OperationType {
  UNKNOWN(0),
  PURCHASE(1),
  INSTALLMENT_PURCHASE(2),
  WITHDRAWAL(3),
  PAYMENT(4);

  private final Integer id;

  public static OperationType getById(int id) {
    for (OperationType operationType : values()) {
      if (operationType.id.equals(id)) {
        return operationType;
      }
    }
    throw new BusinessException(
        String.format("The informed operation type [%s] doesn't exist.", id));
  }
}
