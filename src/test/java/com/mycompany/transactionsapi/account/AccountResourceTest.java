package com.mycompany.transactionsapi.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.math.BigInteger;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
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
    void shouldGetAllAccounts() throws Exception {
    List<Account> accounts = List.of(new Account(new BigInteger("12345678900")), new Account(new BigInteger("12345678922")));

    accountRepository.saveAll(accounts);

    MvcResult result = mockMvc.perform(get("/account")
            .contentType("application/json"))
            .andExpect(status().isOk())
            .andReturn();
            
    ObjectMapper mapper = new ObjectMapper();
    List<Account> resultAccounts = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Account>>() {});
    
    assertEquals(2, resultAccounts.size());
    }

    @Test
    void shouldFindAnAccountByDocumentSuccessfully() throws Exception {
    List<Account> accounts = List.of(new Account(new BigInteger("12345678900")), new Account(new BigInteger("12345678922")));

    accountRepository.saveAll(accounts);

   mockMvc.perform(get("/account/find")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString("12345678922")))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.documentNumber").value("12345678922")) 
            .andReturn();
            
    }
    
    //TODO Isolate tests
    //TODO Test not found
}
