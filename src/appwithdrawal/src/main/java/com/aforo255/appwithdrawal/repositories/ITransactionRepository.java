package com.aforo255.appwithdrawal.repositories;

import com.aforo255.appwithdrawal.models.TransactionModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionRepository extends CrudRepository<TransactionModel, Long>{
    
}
