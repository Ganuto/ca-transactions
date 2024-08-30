package com.project.transactions.controller.data.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AccountCreationRequest {
    @Pattern(regexp = "^[0-9]*$", message = "test")
    @NotNull(message = "'documentNumber' cannot be null.")
    private String documentNumber;
}
