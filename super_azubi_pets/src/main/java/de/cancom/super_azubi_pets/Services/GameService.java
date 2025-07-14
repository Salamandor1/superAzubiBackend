package de.cancom.super_azubi_pets.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import de.cancom.super_azubi_pets.DTOs.CreateAndUpdateGameDTO;
import de.cancom.super_azubi_pets.Models.Game;
import de.cancom.super_azubi_pets.Models.Team;
import de.cancom.super_azubi_pets.Repositories.GameRepository;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepo;

    @Autowired
    private TeamService teamService;

    // Create
    public Game createGame(CreateAndUpdateGameDTO dto) {
        Game newGame = new Game();
        newGame.setHearts(dto.getHearts());
        newGame.setRound(dto.getRounds());
        newGame.setWins(dto.getWins());

        // create GameTeam via GameTeamService from DTO
        Team team = teamService.createTeam(dto.getTeamDTO());
        newGame.setTeam(team);

        return gameRepo.save(newGame);
    }

    // Read
    public List<Game> getAllGames() {
        return gameRepo.findAll();
    }

    // Read
    public Game getGameByID(Long id) {
        return gameRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));
    }

    // Update
    public Game updateGameByID(Long id, CreateAndUpdateGameDTO dto) {
        Game game = getGameByID(id);
        game.setHearts(dto.getHearts());
        game.setWins(dto.getWins());
        game.setRound(dto.getRounds());
        game.setTeam(teamService.updateTeamByID(game.getTeam().getID(), dto.getTeamDTO()));

        return gameRepo.save(game);
    }

}
