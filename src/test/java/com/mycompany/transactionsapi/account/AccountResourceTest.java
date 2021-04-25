package com.mycompany.transactionsapi.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.math.BigInteger;

import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(
  locations = "/application-integrationtest.properties")
public class AccountResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountRepository accountRepository;


    @Test
    void shouldCreateAnAccountSuccessfullyFromResourceToDatabase() throws Exception {
    BigInteger document = new BigInteger("12345678900");

    mockMvc.perform(post("/account")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(document)))
            .andExpect(status().isCreated());

    Account account = accountRepository.findFirstByDocumentNumber(new BigInteger("12345678900"));
    assertEquals(document, account.getDocumentNumber());
    }

    @Test
    @Sql("classpath:com/mycompany/transactionsapi/resources/createAccountList.sql")
    void shouldGetAllAccounts() throws Exception {

        mockMvc.perform(get("/account")
            .contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @Sql("classpath:com/mycompany/transactionsapi/resources/createAccount.sql")
    void shouldGetAnAccountByIdSuccessfully() throws Exception {
    
        mockMvc.perform(get("/account/{accountId}", "1")
            .contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.account_id").value("1")) 
            .andExpect(jsonPath("$.document_number").value("12345678900"));
    }

    @Test
    void shouldNotFoundAnAccountWhenGetById() throws Exception {
    
        mockMvc.perform(get("/account/{accountId}", "3")
            .contentType("application/json"))
            .andExpect(status().isNotFound());
    }

    @Test
    @Sql("classpath:com/mycompany/transactionsapi/resources/createAccount.sql")
    void shouldFindAnAccountByDocumentSuccessfully() throws Exception {
    
        mockMvc.perform(get("/account/find")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString("12345678900")))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.account_id").value("1")) 
            .andExpect(jsonPath("$.document_number").value("12345678900"));
    }

    @Test
    void shouldNotFoundAnAccountWhenFindByDocument() throws Exception {
    
        mockMvc.perform(get("/account/find")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString("12345678922")))
            .andExpect(status().isNotFound());
            
    }
    
}
