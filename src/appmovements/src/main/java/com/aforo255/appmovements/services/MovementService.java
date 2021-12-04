package com.aforo255.appmovements.services;

import com.aforo255.appmovements.models.MovementModel;
import com.aforo255.appmovements.repositories.IMovementRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovementService implements IMovementService {
    @Autowired
	IMovementRepository movementRepository;

    @Override
    public MovementModel add(MovementModel movement) {
        return movementRepository.save(movement);
    }

    @Override
    public Iterable<MovementModel> findByAccountId(Integer accountId) {
        return movementRepository.findByAccountId(accountId);
    }
    
}
