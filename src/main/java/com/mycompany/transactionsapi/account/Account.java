package com.mycompany.transactionsapi.account;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;
	
	@Column(unique=true)
	private BigInteger documentNumber;

	public Account() {
	}

	public Account(BigInteger document) {
		this.documentNumber = document;
	}
	
}
