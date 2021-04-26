package com.mycompany.transactionsapi.account;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.Optional;

import com.mycompany.transactionsapi.exception.NotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
	
	@InjectMocks AccountService accountService; 

	@Mock AccountRepository accountRepository;
	
	@Test
	public void shouldCreateAnAccountSuccessfully() {

		var documentNumber = new BigInteger("12345678900");
		Account account = new Account(documentNumber);
		account.setAccountId(1L);

		when(accountRepository.save(any(Account.class))).thenReturn(account);
		
		var createdAccount = accountService.create(documentNumber);
		assertEquals(documentNumber, createdAccount.getDocumentNumber());
	}

	@Test
	public void shouldGetAnAccountByIdSuccessfuly(){
		Account account = new Account(new BigInteger("12345678900"));
		account.setAccountId(3L);

		when(accountRepository.findById(3L)).thenReturn(Optional.of(account));
		
		var accountFound = accountService.getAccountById(3L);
		assertEquals(3L, accountFound.getAccountId());
	}

	@Test
	public void shouldThrowsNotFoundExceptionWhenTryGetAnExistenResource() {
		
		NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> {
			accountService.getAccountById(2L);
		});
		
		assertTrue(notFoundException.getMessage().contains("Could not find any account with this ID"));
	}

	@Test
	public void shouldFindAnAccountByDocumentSuccessfuly(){
		
		var documentNumber = new BigInteger("12345678900");
		Account account = new Account(documentNumber);
		account.setAccountId(1L);

		when(accountRepository.findFirstByDocumentNumber(documentNumber)).thenReturn(account);
		
		var accountFound = accountService.findByDocument(documentNumber);
		assertEquals(documentNumber, accountFound.getDocumentNumber());
	}

	@Test
	public void shouldThrowsNotFoundExceptionWhenTryFindAnExistentAccount() {
		
		NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> {
			accountService.findByDocument(new BigInteger("12345678900"));
		});
		
		assertTrue(notFoundException.getMessage().contains("Could not find any account with this document"));
	}

}
