package com.mycompany.transactionsapi.account;

import java.math.BigInteger;

import org.springframework.stereotype.Service;

@Service
public class AccountService {


	public AccountService() {
	}
	
	public Account create(String document) {
		return new Account(new BigInteger(document));
	}

}
