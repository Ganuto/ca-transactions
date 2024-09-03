package com.project.transactions.controller.data.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountCreationRequest {
  @Pattern(
      regexp = "^[0-9]*$",
      message = "document_number must match the expression '^[0-9]*$' (number only).")
  @NotBlank(message = "document_number cannot be null.")
  private String documentNumber;
}
