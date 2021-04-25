package com.mycompany.transactionsapi.transactions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import com.mycompany.transactionsapi.account.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private TransactionRepository transactionRepository;
    private AccountService accountService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    public Transaction create(TransactionRequest request) {
        var account = accountService.getAccountById(request.getAccountId());
        
        Transaction transaction = Transaction.builder()
        .account(account)
        .operationTypeId(request.getOperationTypeId())
        .amount(getAmountByOperantionType(request.getOperationTypeId(), request.getAmount()))
        .eventDate(LocalDateTime.now())
        .build();

        return transactionRepository.save(transaction);
    }


    private BigDecimal getAmountByOperantionType(Long operationType, BigDecimal amount) {

        switch (operationType.intValue()) {
            case 1: case 2: case 3:
                if(amount.longValue() > 0) {
                    return amount.negate().setScale(2, RoundingMode.HALF_EVEN);
                }
                return amount.setScale(2, RoundingMode.HALF_EVEN);
            default:
                if(amount.longValue() > 0) {
                    return amount.setScale(2, RoundingMode.HALF_EVEN);
                }
                return amount.negate().setScale(2, RoundingMode.HALF_EVEN);
                
        }
    }
}
