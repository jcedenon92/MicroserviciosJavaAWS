package com.aforo255.appsecurity.repositories;

import com.aforo255.appsecurity.models.AccessModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthRepository extends CrudRepository<AccessModel, Long> {
    
}
