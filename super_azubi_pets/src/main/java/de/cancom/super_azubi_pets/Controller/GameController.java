package de.cancom.super_azubi_pets.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<CreateAndUpdateGameDTO> createGame(@RequestBody CreateAndUpdateGameDTO dto) {
        gameService.createGame(dto);
        return ResponseEntity.ok(dto);
    }

    // GET all
    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        return ResponseEntity.ok(gameService.getAllGames());
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameByID(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.getGameByID(id));
    }

    // PUT by ID
    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGameByID(@PathVariable Long id, @RequestBody CreateAndUpdateGameDTO dto) {
        try {
            Game updatedGame = gameService.updateGameByID(id, dto);
            return ResponseEntity.ok(updatedGame);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

}
