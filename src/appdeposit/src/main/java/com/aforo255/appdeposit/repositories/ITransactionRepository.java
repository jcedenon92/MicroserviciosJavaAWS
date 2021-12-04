package com.aforo255.appdeposit.repositories;

import com.aforo255.appdeposit.models.TransactionModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionRepository extends CrudRepository<TransactionModel, Long>{
    
}
