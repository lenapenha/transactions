package com.mycompany.transactionsapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.math.BigInteger;

import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.transactionsapi.account.Account;
import com.mycompany.transactionsapi.account.AccountRepository;
import com.mycompany.transactionsapi.account.AccountRequest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@Transactional
public class AccountResourceTest extends BaseIntegrationTest{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountRepository accountRepository;


    @Test
    public void shouldCreateAnAccountSuccessfullyFromResourceToDatabase() throws Exception {
    AccountRequest request = new AccountRequest(new BigInteger("12345678900"));

    mockMvc.perform(post("/account")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated());

    Account account = accountRepository.findFirstByDocumentNumber(new BigInteger("12345678900"));
    assertEquals(request.getDocumentNumber(), account.getDocumentNumber());
    }

    @Test
    @Sql("classpath:createAccountList.sql")
    public void shouldGetAllAccounts() throws Exception {

        mockMvc.perform(get("/account")
            .contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @Sql("classpath:createAccount.sql")
    public void shouldGetAnAccountByIdSuccessfully() throws Exception {
    
        mockMvc.perform(get("/account/{accountId}", "1")
            .contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.account_id").value("1")) 
            .andExpect(jsonPath("$.document_number").value("12345678900"));
    }

    @Test
    public void shouldNotFoundAnAccountWhenGetById() throws Exception {
    
        mockMvc.perform(get("/account/{accountId}", "3")
            .contentType("application/json"))
            .andExpect(status().isNotFound());
    }

    @Test
    @Sql("classpath:createAccount.sql")
    public void shouldFindAnAccountByDocumentSuccessfully() throws Exception {
    
        mockMvc.perform(get("/account/find")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString("12345678900")))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.account_id").value("1")) 
            .andExpect(jsonPath("$.document_number").value("12345678900"));
    }

    @Test
    public void shouldNotFoundAnAccountWhenFindByDocument() throws Exception {
    
        mockMvc.perform(get("/account/find")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString("12345678922")))
            .andExpect(status().isNotFound());
            
    }
    
}
