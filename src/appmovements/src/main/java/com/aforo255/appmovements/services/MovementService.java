package com.aforo255.appmovements.services;

import com.aforo255.appmovements.models.MovementModel;
import com.aforo255.appmovements.repositories.IMovementRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
@CacheConfig(cacheNames = "movement")
@Service
public class MovementService implements IMovementService {

    @Autowired
	IMovementRepository movementRepository;

    Logger logger = LoggerFactory.getLogger(MovementService.class);

    @Override
    public MovementModel add(MovementModel movement) {
        return movementRepository.save(movement);
    }

    @Override
    @Cacheable(cacheNames = {"movement"}, key = "#accountId")
    public Iterable<MovementModel> findByAccountId(Integer accountId) {

        logger.info("SERVICE: Get Find By AccountId: {}", accountId);
        return movementRepository.findByAccountId(accountId);
    }
    
}
