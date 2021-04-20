package com.mycompany.transactionsapi.account;

import java.math.BigInteger;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Account {
	
	private Long accountId;
	private BigInteger documentNumber;
	
	public Account(BigInteger document) {
		this.documentNumber = document;
	}
	
}
