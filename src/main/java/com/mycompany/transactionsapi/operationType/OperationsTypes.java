package com.mycompany.transactionsapi.operationType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;

@Getter
@Entity
public class OperationsTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long operationTypeId;

    @Column(unique=true)
    private String opDescription;

    public OperationsTypes(){};
    
}
