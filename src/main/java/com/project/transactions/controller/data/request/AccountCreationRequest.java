package com.project.transactions.controller.data.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AccountCreationRequest {
    @NotNull(message = "'documentNumber' cannot be null")
    private Long documentNumber;
}
