package com.mycompany.transactionsapi.account;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.mycompany.transactionsapi.exception.NotFoundException;

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

	public Account getAccountById(Long accountId) {
		return accountRepository.findById(accountId)
		.orElseThrow(() -> new NotFoundException("Could not find any account with this ID"));
	}

	public Account findByDocument(BigInteger document) {
		return Optional.ofNullable(accountRepository.findFirstByDocumentNumber(document))
		.orElseThrow(() -> new NotFoundException("Could not find any account with this document"));
	}

    public List<Account> getAllAccounts() {
        return this.accountRepository.findAll();
    }

}
