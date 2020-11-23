package com.pjh.mongodb.study;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spaceship")
@RequiredArgsConstructor
public class SpaceShipRestController {
    private final SpaceShipService spaceShipService;

    @GetMapping("/")
    public List<SpaceShip> ships(@RequestParam String captainName){
        return spaceShipService.findForCaptain(captainName);
    }

    @GetMapping("/all")
    public List<SpaceShip> allShips(){
        return spaceShipService.allTheSourcer();
    }
}
