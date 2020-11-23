package com.pjh.mongodb.study;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpaceShipRepository extends MongoRepository<SpaceShip, String> {
}
