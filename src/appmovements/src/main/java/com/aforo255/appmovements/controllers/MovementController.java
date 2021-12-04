package com.aforo255.appmovements.controllers;

import com.aforo255.appmovements.models.MovementModel;
import com.aforo255.appmovements.services.IMovementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/movement")
public class MovementController {
    
	Logger logger = LoggerFactory.getLogger(MovementController.class);

	@Autowired
    IMovementService movementService;

    @GetMapping ("/{accountId}")
	public ResponseEntity<?> getByAccountId (@PathVariable Integer accountId){
		
		logger.info("Get By AccountId: {}", accountId);
		
		Iterable<MovementModel> transaction= movementService.findByAccountId(accountId);
		return ResponseEntity.ok(transaction);
	}
}
