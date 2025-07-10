package de.cancom.super_azubi_pets.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import de.cancom.super_azubi_pets.DTOs.CreateAndUpdateGameTeamDTO;
import de.cancom.super_azubi_pets.Models.GameTeam;
import de.cancom.super_azubi_pets.Repositories.GameTeamRepository;

@Service
public class GameTeamService {

    @Autowired
    private GameTeamRepository teamRepo;

    @Autowired
    private TeamAnimalService teamAnimalService;

    // Create
    public GameTeam createTeam(CreateAndUpdateGameTeamDTO dto) {
        GameTeam team = new GameTeam();
        // Create TeamAnimals from dto via service

        team.setSlot0(teamAnimalService.createTeamAnimal(dto.getSlot0()));
        team.setSlot1(teamAnimalService.createTeamAnimal(dto.getSlot1()));
        team.setSlot2(teamAnimalService.createTeamAnimal(dto.getSlot2()));
        team.setSlot3(teamAnimalService.createTeamAnimal(dto.getSlot3()));
        team.setSlot4(teamAnimalService.createTeamAnimal(dto.getSlot4()));

        return teamRepo.save(team);
    }

    // Read
    public List<GameTeam> getAllTeams() {
        return teamRepo.findAll();
    }

    // Read
    public GameTeam findTeamByID(Long id) {
        return teamRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found"));
    }

}
