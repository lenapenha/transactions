package com.mycompany.transactionsapi.account;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Account {
	
	@Id
	@GeneratedValue
	private Long accountId;
	
	private BigInteger documentNumber;
	
	public Account(BigInteger document) {
		this.documentNumber = document;
	}
	
}
