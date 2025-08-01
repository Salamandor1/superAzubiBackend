package de.cancom.super_azubi_pets.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.cancom.super_azubi_pets.Models.Log;
import de.cancom.super_azubi_pets.Services.FightService;
import de.cancom.super_azubi_pets.Services.GameService;

@RestController
@RequestMapping("/fight")
public class FightController {

    @Autowired
    private FightService fightService;

    @Autowired
    private GameService gameService;

    // ResolveFight
    @GetMapping("/{gameID}")
    public ResponseEntity<Log> resolveFight(@PathVariable Long gameID) {
        Log log = fightService.resolveFight(gameID);
        gameService.copyGame(gameID);
        return ResponseEntity.ok(log);
    }

}
