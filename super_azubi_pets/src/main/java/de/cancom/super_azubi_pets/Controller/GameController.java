package de.cancom.super_azubi_pets.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.cancom.super_azubi_pets.DTOs.CreateAndUpdateGameDTO;
import de.cancom.super_azubi_pets.Models.Game;
import de.cancom.super_azubi_pets.Services.GameService;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    // POST
    @PostMapping
    public ResponseEntity<Long> createGame(@RequestBody CreateAndUpdateGameDTO dto) {
        Game game = gameService.saveGame(dto);
    }

}
