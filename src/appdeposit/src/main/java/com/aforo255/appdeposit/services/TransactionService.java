package com.aforo255.appdeposit.services;

import javax.transaction.Transactional;

import com.aforo255.appdeposit.models.TransactionModel;
import com.aforo255.appdeposit.repositories.ITransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService  implements ITransactionService {

    @Autowired
	private ITransactionRepository transactionRepositoy;

    @Override
    @Transactional
    public TransactionModel add(TransactionModel transactionModel) {
        return transactionRepositoy.save(transactionModel);
    }
    
}
