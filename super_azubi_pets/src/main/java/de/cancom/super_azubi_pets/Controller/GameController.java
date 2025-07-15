package de.cancom.super_azubi_pets.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.cancom.super_azubi_pets.DTOs.GameCreateDTO;
import de.cancom.super_azubi_pets.DTOs.GameResponseDTO;
import de.cancom.super_azubi_pets.DTOs.GameUpdateDTO;
import de.cancom.super_azubi_pets.Services.GameService;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    // POST
    @PostMapping
    public ResponseEntity<GameResponseDTO> createGame(@RequestBody GameCreateDTO dto) {
        GameResponseDTO response = gameService.createGame(dto);
        return ResponseEntity.ok(response);
    }

    // GET all
    @GetMapping
    public ResponseEntity<List<GameResponseDTO>> getAllGames() {
        return ResponseEntity.ok(gameService.getAllGames());
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<GameResponseDTO> getGameByID(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.getGameByID(id));
    }

    // PUT by ID
    @PutMapping("/{id}")
    public ResponseEntity<GameResponseDTO> updateGameByID(@PathVariable Long id, @RequestBody GameUpdateDTO dto) {
        try {
            GameResponseDTO updatedGame = gameService.updateGameByID(id, dto);
            return ResponseEntity.ok(updatedGame);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.deleteGameByID(id);
        return ResponseEntity.noContent().build();
    }

}
