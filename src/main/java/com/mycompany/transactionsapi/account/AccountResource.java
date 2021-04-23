package com.mycompany.transactionsapi.account;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountResource {

    private final AccountService accountService;

    @Autowired
    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody BigInteger document){
       return ResponseEntity.status(HttpStatus.CREATED).body(this.accountService.create(document));
    }


    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok().body(this.accountService.getAllAccounts());
    }

    @GetMapping("/find")
    public ResponseEntity<Account> findAccountByDocument(@RequestBody BigInteger document) {
        return ResponseEntity.ok().body(this.accountService.findByDocument(document));
    }

}
