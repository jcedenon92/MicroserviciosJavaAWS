package com.aforo255.appmovements.repositories;

import com.aforo255.appmovements.models.MovementModel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovementRepository extends MongoRepository<MovementModel, String>  {
    @Query("{'accountId':?0}")
	public Iterable<MovementModel> findByAccountId(Integer accountId);
}
