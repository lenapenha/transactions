package com.mycompany.transactionsapi.transactions;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.mycompany.transactionsapi.account.Account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Transactions {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionsId;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "operation_type_id", nullable = false)
    private Transactions transaction;

    private BigDecimal amount;

    private LocalDateTime eventDate;

    public Transactions(){};
    
}
