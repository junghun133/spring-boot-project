package com.pjh.mongodb.study;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpaceShipService {

    private final MongoTemplate mongoTemplate;

    public List<SpaceShip> allTheSourcer(){
        Query query = new Query()
                .with(Sort.by(Sort.Order.desc("engines")))
                .limit(3);

        return mongoTemplate.find(query, SpaceShip.class);
    }

    public List<SpaceShip> findForCaptain(String name){
        Query query = new Query()
                .addCriteria(Criteria.where("captain.name").is(name))
                .with(Sort.by(Sort.Order.desc("engines")))
                .limit(4);

        return mongoTemplate.find(query, SpaceShip.class);
    }
}
