package com.mycompany.transactionsapi.Integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.transactionsapi.transactions.Transaction;
import com.mycompany.transactionsapi.transactions.TransactionRepository;
import com.mycompany.transactionsapi.transactions.TransactionRequest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@AutoConfigureMockMvc
@Transactional
@Sql("classpath:com/mycompany/transactionsapi/resources/createAccount.sql")
public class TrasanctionResourceTest extends BaseIntegrationTest{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired 
    private TransactionRepository transactionRepository;

    @Test
    public void shouldCreatePaymentTransactionWithPositiveAmountSuccessfully() throws Exception {
        var transaction = new TransactionRequest(1l, 4l, new BigDecimal(123.45));

        MvcResult result =  mockMvc.perform(post("/transaction")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(transaction)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.account.account_id").value("1"))
            .andExpect(jsonPath("$.operation_type_id").value("4"))
            .andExpect(jsonPath("$.amount").value("123.45"))
            .andReturn();
        
        Transaction transactionMapper = 
            objectMapper.readValue(result.getResponse().getContentAsString(), Transaction.class);

        var transactionInDB = transactionRepository.findById(transactionMapper.getTransactionId());
        assertEquals(transactionMapper.getAccount().getAccountId(), transactionInDB.get().getAccount().getAccountId());
        assertEquals(transactionMapper.getAmount(), transactionInDB.get().getAmount());
    }

    @Test
    public void shouldCreatePaymentTransactionWithNegativeAmountsSuccessfully() throws Exception {
        var transaction = new TransactionRequest(1l, 4l, new BigDecimal(-123.45));

        MvcResult result =  mockMvc.perform(post("/transaction")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(transaction)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.account.account_id").value("1"))
            .andExpect(jsonPath("$.operation_type_id").value("4"))
            .andExpect(jsonPath("$.amount").value("123.45"))
            .andReturn();

        Transaction transactionMapper = 
            objectMapper.readValue(result.getResponse().getContentAsString(), Transaction.class);

        var transactionInDB = transactionRepository.findById(transactionMapper.getTransactionId());
        assertEquals(transactionMapper.getAccount().getAccountId(), transactionInDB.get().getAccount().getAccountId());
        assertEquals(transactionMapper.getAmount(), transactionInDB.get().getAmount());
    }

    @Test
    public void shouldCreateWithdrawTransactionWithPositiveAmountsSuccessfully() throws Exception {
        var transaction = new TransactionRequest(1l, 3l, new BigDecimal(123.45));

        MvcResult result =  mockMvc.perform(post("/transaction")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(transaction)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.account.account_id").value("1"))
            .andExpect(jsonPath("$.operation_type_id").value("3"))
            .andExpect(jsonPath("$.amount").value("-123.45"))
            .andReturn();

        Transaction transactionMapper = 
            objectMapper.readValue(result.getResponse().getContentAsString(), Transaction.class);

        var transactionInDB = transactionRepository.findById(transactionMapper.getTransactionId());
        assertEquals(transactionMapper.getAccount().getAccountId(), transactionInDB.get().getAccount().getAccountId());
        assertEquals(transactionMapper.getAmount(), transactionInDB.get().getAmount());
    }

    @Test
    public void shouldCreateWithdrawTransactionWithNegativeAmountsSuccessfully() throws Exception {
        var transaction = new TransactionRequest(1l, 3l, new BigDecimal(-123.45));

        MvcResult result =  mockMvc.perform(post("/transaction")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(transaction)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.account.account_id").value("1"))
            .andExpect(jsonPath("$.operation_type_id").value("3"))
            .andExpect(jsonPath("$.amount").value("-123.45"))
            .andReturn();

        Transaction transactionMapper = 
            objectMapper.readValue(result.getResponse().getContentAsString(), Transaction.class);

        var transactionInDB = transactionRepository.findById(transactionMapper.getTransactionId());
        assertEquals(transactionMapper.getAccount().getAccountId(), transactionInDB.get().getAccount().getAccountId());
        assertEquals(transactionMapper.getAmount(), transactionInDB.get().getAmount());
    }

    @Test
    public void shouldReturn404WhenTryCreateATransactionForAnInexistenAccount() throws Exception {
        var transaction = new TransactionRequest(3l, 4l, new BigDecimal(123.45));

        mockMvc.perform(post("/transaction")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(transaction)))
            .andExpect(status().isNotFound());
    }
    
}
