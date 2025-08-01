package de.cancom.super_azubi_pets.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import de.cancom.super_azubi_pets.DTOs.GameCreateDTO;
import de.cancom.super_azubi_pets.DTOs.GameResponseDTO;
import de.cancom.super_azubi_pets.DTOs.GameUpdateDTO;
import de.cancom.super_azubi_pets.Models.Game;
import de.cancom.super_azubi_pets.Models.Team;
import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Repositories.GameRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepo;

    @Autowired
    private TeamService teamService;

    // Create
    public GameResponseDTO createGame(GameCreateDTO dto) {
        Game newGame = new Game();
        newGame.setHearts(dto.getHearts());
        if (newGame.getHearts() < 5) {
            newGame.setHearts(5);
        }
        newGame.setRound(1);
        newGame.setWins(0);

        // create GameTeam via GameTeamService from DTO
        Team team = teamService.createTeam();
        newGame.setTeam(team);

        gameRepo.save(newGame);

        return new GameResponseDTO(newGame);
    }

    // Save
    public void saveGame(Game game) {
        gameRepo.save(game);
    }

    // Read
    public List<GameResponseDTO> getAllGames() {
        List<Game> games = gameRepo.findAll();
        List<GameResponseDTO> responses = new ArrayList<>();
        for (Game game : games) {
            responses.add(new GameResponseDTO(game));
        }
        return responses;
    }

    // Read
    public GameResponseDTO getGameByID(Long id) {
        Game game = fetchGame(id);
        return new GameResponseDTO(game);
    }

    // Update
    public GameResponseDTO updateGameByID(Long id, GameUpdateDTO dto) {
        Game game = fetchGame(id);
        Team team = teamService.createTeam(dto.getTeamDTO());
        game.setTeam(team);
        gameRepo.save(game);
        return new GameResponseDTO(game);
    }

    // Update
    public GameResponseDTO updateTeamByID(Long gameID, Long teamID) {
        Game game = fetchGame(gameID);
        Team team = teamService.getTeamByID(teamID);
        game.setTeam(team);
        gameRepo.save(game);
        return new GameResponseDTO(game);
    }

    // Fetch
    private Game fetchGame(Long id) {
        return gameRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));
    }

    // Fetch all
    public List<Game> fetchAllGames() {
        return gameRepo.findAll();
    }

    // Delete
    public void deleteGameByID(Long id) {
        if (gameRepo.existsById(id)) {
            gameRepo.deleteById(id);
        } else
            throw new EntityNotFoundException("Game with ID " + id + " not found.");
    }

    // Delete
    public void deleteAllGames() {
        gameRepo.deleteAll();
        gameRepo.resetIDSequence();
    }

    // Copy
    public void copyGame(Long id) {
        Game game = fetchGame(id);
        List<TeamAnimal> team = game.getTeam().getAllAnimals();
        if (team.stream().allMatch(Objects::isNull)) {
            return;
        }
        Game copy = new Game(game);
        gameRepo.save(copy);
    }

    // Find by Status
    public Game findGameByStatus(Game game) {
        int hearts = game.getHearts();
        int rounds = game.getRounds();
        int wins = game.getWins();
        Optional<Game> result = gameRepo.findGameByStatus(game.getGameID(), hearts - 2, hearts + 2, rounds, wins - 2,
                wins + 2);
        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

}
