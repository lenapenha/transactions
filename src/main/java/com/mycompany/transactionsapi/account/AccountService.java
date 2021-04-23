package com.mycompany.transactionsapi.account;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

	private AccountRepository accountRepository;

	@Autowired
	public AccountService(final AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	public Account create(BigInteger document) {
		Account account = new Account(document);
		return this.accountRepository.save(account);
	}

	public Account findByDocument(BigInteger document) {
		return this.accountRepository.findFirstByDocumentNumber(document);
	}

    public List<Account> getAllAccounts() {
        return this.accountRepository.findAll();
    }

}
