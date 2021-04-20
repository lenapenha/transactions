package com.mycompany.transactionsapi.account;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountServiceTest {
	
	@Autowired AccountService accountService; 
	
	@Test
	public void shouldCreateAnAccountSuccessfully() {
		var documentNumber = new String("12345678900");
		var createdAccount = accountService.create(documentNumber);
		assertEquals(documentNumber, createdAccount.getDocumentNumber().toString());
	}

}
