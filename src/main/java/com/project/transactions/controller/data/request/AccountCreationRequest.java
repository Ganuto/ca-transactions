package com.project.transactions.controller.data.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AccountCreationRequest {
    @NotBlank(message = "'documentNumber' cannot be null.")
    private String documentNumber;
}
