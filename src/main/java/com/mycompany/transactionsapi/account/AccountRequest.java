package com.mycompany.transactionsapi.account;

import java.math.BigInteger;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class AccountRequest {
    @NotNull(message = "document_number is mandatory")
    private BigInteger documentNumber;
}
