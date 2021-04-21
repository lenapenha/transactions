package com.mycompany.transactionsapi.account;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

	private AccountRepository accountRepository;

	@Autowired
	public AccountService(final AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	public Account create(String document) {
		Account account = new Account(new BigInteger(document));
		return this.accountRepository.save(account);
	}

	public Account findByDocument(String document) {
		return this.accountRepository.findFirstByDocumentNumber(new BigInteger(document));
	}

}
