package com.mycompany.transactionsapi.transactions;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class TransactionRequest {
    @NotNull(message = "account_id is mandatory")
    private Long accountId;

    @NotNull(message = "operation_type_id is mandatory")
    private Long operationTypeId;

    @NotNull(message = "amount is mandatory")
    private BigDecimal amount;

    public TransactionRequest() {}

    public TransactionRequest(Long accountId, Long operationTypeId, BigDecimal amount) {
        this.accountId = accountId;
        this.operationTypeId = operationTypeId;
        this.amount = amount;
    }  

}
