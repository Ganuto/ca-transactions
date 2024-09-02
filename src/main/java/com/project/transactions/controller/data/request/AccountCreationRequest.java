package com.project.transactions.controller.data.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountCreationRequest {
  @Pattern(regexp = "^[0-9]*$")
  @NotBlank(message = "'documentNumber' cannot be null.")
  private String documentNumber;
}
