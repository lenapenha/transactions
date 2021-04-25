package com.mycompany.transactionsapi.transactions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.mycompany.transactionsapi.account.Account;
import com.mycompany.transactionsapi.account.AccountService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import lombok.var;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks TransactionService transactionService;

    @Mock TransactionRepository transactionRepository;

    @Mock AccountService accountService;

    @Test
    public void shouldCreateATransactionTypeCashPurchaseWithPositiveAmountRequestSuccessfully() {
        var transactionRequest = new TransactionRequest(1L, 1L, new BigDecimal(123.45));

        Transaction transactionToPersist = Transaction.builder()
        .account(new Account())
        .operationTypeId(transactionRequest.getOperationTypeId())
        .amount(new BigDecimal(-123.45))
        .eventDate(LocalDateTime.now())
        .build();

        when(accountService.getAccountById(anyLong())).thenReturn(new Account());
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transactionToPersist);

        var createdTransaction = transactionService.create(transactionRequest);
        assertEquals(new BigDecimal(-123.45), createdTransaction.getAmount());
    }

    @Test
    public void shouldCreateATransactionTypeCashPurchaseWithNegativeAmountRequestSuccessfully() {
        var transactionRequest = new TransactionRequest(1L, 1L, new BigDecimal(-123.45));

        Transaction transactionToPersist = Transaction.builder()
        .account(new Account())
        .operationTypeId(transactionRequest.getOperationTypeId())
        .amount(new BigDecimal(-123.45))
        .eventDate(LocalDateTime.now())
        .build();

        when(accountService.getAccountById(anyLong())).thenReturn(new Account());
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transactionToPersist);

        var createdTransaction = transactionService.create(transactionRequest);
        assertEquals(new BigDecimal(-123.45), createdTransaction.getAmount());
    }

    @Test
    public void shouldCreateATransactionTypeInstallmentPurchaseWithPositiveAmountRequestSuccessfully() {
        var transactionRequest = new TransactionRequest(1L, 2L, new BigDecimal(123.45));

        Transaction transactionToPersist = Transaction.builder()
        .account(new Account())
        .operationTypeId(transactionRequest.getOperationTypeId())
        .amount(new BigDecimal(-123.45))
        .eventDate(LocalDateTime.now())
        .build();

        when(accountService.getAccountById(anyLong())).thenReturn(new Account());
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transactionToPersist);

        var createdTransaction = transactionService.create(transactionRequest);
        assertEquals(new BigDecimal(-123.45), createdTransaction.getAmount());
    }

    @Test
    public void shouldCreateATransactionTypeInstallmentPurchaseWithNegativeAmountRequestSuccessfully() {
        var transactionRequest = new TransactionRequest(1L, 2L, new BigDecimal(-123.45));

        Transaction transactionToPersist = Transaction.builder()
        .account(new Account())
        .operationTypeId(transactionRequest.getOperationTypeId())
        .amount(new BigDecimal(-123.45))
        .eventDate(LocalDateTime.now())
        .build();

        when(accountService.getAccountById(anyLong())).thenReturn(new Account());
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transactionToPersist);

        var createdTransaction = transactionService.create(transactionRequest);
        assertEquals(new BigDecimal(-123.45), createdTransaction.getAmount());
    }

    @Test
    public void shouldCreateATransactionTypeWithdrawWithPositiveAmountRequestSuccessfully() {
        var transactionRequest = new TransactionRequest(1L, 3L, new BigDecimal(123.46));

        Transaction transactionToPersist = Transaction.builder()
        .account(new Account())
        .operationTypeId(transactionRequest.getOperationTypeId())
        .amount(new BigDecimal(-123.45))
        .eventDate(LocalDateTime.now())
        .build();

        when(accountService.getAccountById(anyLong())).thenReturn(new Account());
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transactionToPersist);

        var createdTransaction = transactionService.create(transactionRequest);
        assertEquals(new BigDecimal(-123.45), createdTransaction.getAmount());
    }

    @Test
    public void shouldCreateATransactionTypeWithdrawWithNegativeAmountRequestSuccessfully() {
        var transactionRequest = new TransactionRequest(1L, 3L, new BigDecimal(-123.45));

        Transaction transactionToPersist = Transaction.builder()
        .account(new Account())
        .operationTypeId(transactionRequest.getOperationTypeId())
        .amount(new BigDecimal(-123.45))
        .eventDate(LocalDateTime.now())
        .build();

        when(accountService.getAccountById(anyLong())).thenReturn(new Account());
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transactionToPersist);

        var createdTransaction = transactionService.create(transactionRequest);
        assertEquals(new BigDecimal(-123.45), createdTransaction.getAmount());
    }

    @Test
    public void shouldCreateATransactionTypePaymentWithPositiveAmountRequestSuccessfully() {
        var transactionRequest = new TransactionRequest(1L, 4L, new BigDecimal(123.45));

        Transaction transactionToPersist = Transaction.builder()
        .account(new Account())
        .operationTypeId(transactionRequest.getOperationTypeId())
        .amount(new BigDecimal(123.45))
        .eventDate(LocalDateTime.now())
        .build();

        when(accountService.getAccountById(anyLong())).thenReturn(new Account());
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transactionToPersist);

        var createdTransaction = transactionService.create(transactionRequest);
        assertEquals(new BigDecimal(123.45), createdTransaction.getAmount());
    }

    @Test
    public void shouldCreateATransactionTypePaymentWithNegativeAmountRequestSuccessfully() {
        var transactionRequest = new TransactionRequest(1L, 4L, new BigDecimal(-123.45));

        Transaction transactionToPersist = Transaction.builder()
        .account(new Account())
        .operationTypeId(transactionRequest.getOperationTypeId())
        .amount(new BigDecimal(123.45))
        .eventDate(LocalDateTime.now())
        .build();

        when(accountService.getAccountById(anyLong())).thenReturn(new Account());
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transactionToPersist);

        var createdTransaction = transactionService.create(transactionRequest);
        assertEquals(new BigDecimal(123.45), createdTransaction.getAmount());
    }
    
}
