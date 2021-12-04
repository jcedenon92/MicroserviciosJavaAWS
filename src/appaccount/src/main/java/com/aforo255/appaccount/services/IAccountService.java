package com.aforo255.appaccount.services;

import java.util.List;

import com.aforo255.appaccount.models.AccountModel;

public interface IAccountService {
    
    public List<AccountModel> findAll();
    public AccountModel findById (Integer id); 
    public AccountModel update(AccountModel accountModel);
}
