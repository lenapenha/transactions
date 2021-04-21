package com.mycompany.transactionsapi.account;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
	
	@InjectMocks AccountService accountService; 
	
	@Test
	public void shouldCreateAnAccountSuccessfully() {
		//mockar retorno
		var documentNumber = new String("12345678900");
		var createdAccount = accountService.create(documentNumber);
		assertEquals(documentNumber, createdAccount.getDocumentNumber().toString());
	}

	@Test
	public void shouldFindAnAccountByDocumentSuccessfuly(){
		//mockar retorno
		var documentNumber = new String("12345678900");
		var account = accountService.findByDocument(documentNumber);
		assertEquals(documentNumber, account.getDocumentNumber().toString());
	}

}
