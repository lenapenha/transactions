package com.mycompany.transactionsapi.account;

import java.math.BigInteger;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountRequest {
    @NotNull(message = "document_number is mandatory")
    private BigInteger documentNumber;

    public AccountRequest() {};
}
