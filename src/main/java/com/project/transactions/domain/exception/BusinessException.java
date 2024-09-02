package com.project.transactions.domain.exception;

public class BusinessException extends RuntimeException {

  public BusinessException(String message) {
    super(message);
  }
}
