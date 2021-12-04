package com.aforo255.appaccount.controllers;

import java.util.List;

import com.aforo255.appaccount.models.AccountModel;
import com.aforo255.appaccount.services.IAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
	private IAccountService accountService;

    @GetMapping()
	public List<AccountModel> get() {
		return accountService.findAll();
	}
}
