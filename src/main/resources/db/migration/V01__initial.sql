CREATE TABLE IF NOT EXISTS ACCOUNTS (
    account_id BIGINT PRIMARY KEY,
    document_number NUMERIC UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS OPERATION_TYPES (
    operation_type_id BIGINT PRIMARY KEY,
    op_description VARCHAR ( 25 ) UNIQUE NOT NULL
);

INSERT INTO OPERATION_TYPES (operation_type_id,  op_description) 
VALUES 
(1, 'COMPRA A VISTA'), 
(2, 'COMPRA PARCELADA'), 
(3, 'SAQUE'), 
(4, 'PAGAMENTO');

CREATE TABLE IF NOT EXISTS TRANSACTIONS (
    transaction_id BIGINT PRIMARY KEY,
    account_id BIGINT,
    operation_type_id BIGINT,
    amount NUMERIC NOT NULL,
    event_date TIMESTAMP NOT NULL,
    CONSTRAINT fk_account
      FOREIGN KEY(account_id) 
	  REFERENCES ACCOUNTS(account_id),
    CONSTRAINT fk_operation_type
      FOREIGN KEY(operation_type_id) 
	  REFERENCES OPERATION_TYPES(operation_type_id)
);


