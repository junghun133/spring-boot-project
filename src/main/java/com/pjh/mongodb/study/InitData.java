package com.pjh.mongodb.study;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitData {

    private final SpaceShipRepository spaceShipRepository;

    @PostConstruct
    public void createInitData(){
        spaceShipRepository.deleteAll();
        spaceShipRepository.saveAll(
                List.of(
                        new SpaceShip(null, "ARocket", 3, new Captain("PJH_24", 5)),
                        new SpaceShip(null, "BRocket", 2, new Captain("PJH_25", 5)),
                        new SpaceShip(null, "CRocket", 9, new Captain("PJH_26", 3)),
                        new SpaceShip(null, "DRocket", 14, new Captain("PJH_27", 2)),
                        new SpaceShip(null, "ERocket", 5, new Captain("PJH_28", 4)),
                        new SpaceShip(null, "FRocket", 1, new Captain("PJH_29", 5))
                )
        );
    }
}
