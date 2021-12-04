package com.aforo255.appwithdrawal.controllers;

import com.aforo255.appwithdrawal.dtos.TransactionRequest;
import com.aforo255.appwithdrawal.messages.TransactionMessagePublish;
import com.aforo255.appwithdrawal.models.TransactionModel;
import com.aforo255.appwithdrawal.services.ITransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/transaction")
public class TransactionControlller {

    @Autowired
    ITransactionService transactionService;

    @Autowired
    TransactionMessagePublish messageEvent;

    Logger logger = LoggerFactory.getLogger(TransactionControlller.class);

    @PostMapping("/withdrawal")
    public ResponseEntity<?> withdrawal(@RequestBody TransactionRequest request) throws Exception {

        logger.info("Post: AccountId {} - Ammount {}", request.getAccountId(), request.getAmount());
        TransactionModel transactionModel = new TransactionModel();
        transactionModel.setAccountId(request.getAccountId());
        transactionModel.setAmount(request.getAmount());
        transactionModel.setType("withdrawal");
        transactionModel = transactionService.add(transactionModel);

        messageEvent.sendDepositEvent(transactionModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(transactionModel);
    }
}
