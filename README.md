# Transactions-API
An API to persist transactions of an account

## Built With

- Java 11
- Spring Boot 2.4

## How to run
    make run

## Samples
### Post Account
    curl -X POST -H 'Content-Type: application/json' -d '{"document_number": 12345678900}' http://localhost:8080/account/
    
### Get Account By Id
    curl -X GET -H 'Content-Type: application/json' http://localhost:8080/account/1

## Post a Transaction
    curl -X POST -H 'Content-Type: application/json' -d '{"account_id": 1, "operation_type_id": 3, "amount": 51.23}' http://localhost:8080/transaction/


## How to test
### Unit Test
    make test/unit

### Integration Test
    make test/integration

### All tests
    make test/all
     