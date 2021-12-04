package com.aforo255.appmovements.services;

import com.aforo255.appmovements.models.MovementModel;

public interface IMovementService {
    public MovementModel add (MovementModel movement);
	public Iterable<MovementModel> findByAccountId (Integer accountId);
}
