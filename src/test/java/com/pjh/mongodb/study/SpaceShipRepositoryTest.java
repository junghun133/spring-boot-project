package com.pjh.mongodb.study;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpaceShipRepositoryTest {

    @Autowired
    SpaceShipRepository spaceShipRepository;

    @Autowired
    SpaceShipService spaceShipService;

  /*  @Test
    public void createRows(){
        spaceShipRepository.deleteAll();
        spaceShipRepository.save(new SpaceShip(null, "sourcer", 4));
        spaceShipRepository.save(new SpaceShip(null, "sourcer", 3));
        spaceShipRepository.save(new SpaceShip(null, "sourcer", 5));
        spaceShipRepository.save(new SpaceShip(null, "sourcer", 1));
        spaceShipRepository.save(new SpaceShip(null, "sourcer", 20));
        spaceShipRepository.save(new SpaceShip(null, "fork", 4));
        spaceShipRepository.save(new SpaceShip(null, "freesbee", 1));
        spaceShipRepository.save(new SpaceShip(null, "rocket", 30));

//        spaceShipRepository.findAll().forEach(System.out::println);

        System.out.println("*** sourcer 검색 ***");
        spaceShipService.allTheSourcer().forEach(System.out::println);
        System.out.println("*** done ***");
    }*/
}