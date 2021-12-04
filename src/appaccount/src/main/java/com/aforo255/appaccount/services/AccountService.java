package com.aforo255.appaccount.services;

import java.util.List;

import com.aforo255.appaccount.models.AccountModel;
import com.aforo255.appaccount.repositories.IAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {

    @Autowired
	IAccountRepository accountRepository;

    @Override
    public List<AccountModel> findAll() {
        return  (List<AccountModel>)accountRepository.findAll();
    }

    @Override
    public AccountModel update(AccountModel accountModel) {
        return accountRepository.save(accountModel);
    }

    @Override
    public AccountModel findById(Integer id) {
        return accountRepository.findById(id).orElse(null);
    }
    
}
