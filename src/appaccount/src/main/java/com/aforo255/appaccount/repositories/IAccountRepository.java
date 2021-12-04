package com.aforo255.appaccount.repositories;

import com.aforo255.appaccount.models.AccountModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends CrudRepository<AccountModel,Integer> {
    
}
