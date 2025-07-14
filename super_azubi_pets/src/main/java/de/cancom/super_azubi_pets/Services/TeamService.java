package de.cancom.super_azubi_pets.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import de.cancom.super_azubi_pets.DTOs.CreateAndUpdateTeamDTO;
import de.cancom.super_azubi_pets.Models.Team;
import de.cancom.super_azubi_pets.Repositories.TeamRepository;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepo;

    @Autowired
    private TeamAnimalService teamAnimalService;

    // Create
    public Team createTeam(CreateAndUpdateTeamDTO dto) {
        Team team = new Team();
        // Create TeamAnimals from dto via service

        team.setSlot0(teamAnimalService.createTeamAnimal(dto.getSlot0()));
        team.setSlot1(teamAnimalService.createTeamAnimal(dto.getSlot1()));
        team.setSlot2(teamAnimalService.createTeamAnimal(dto.getSlot2()));
        team.setSlot3(teamAnimalService.createTeamAnimal(dto.getSlot3()));
        team.setSlot4(teamAnimalService.createTeamAnimal(dto.getSlot4()));

        return teamRepo.save(team);
    }

    // Read
    public List<Team> getAllTeams() {
        return teamRepo.findAll();
    }

    // Read
    public Team getTeamByID(Long id) {
        return teamRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found"));
    }

    // Update
    public Team updateTeamByID(Long id, CreateAndUpdateTeamDTO dto) {
        Team team = getTeamByID(id);
        team.setSlot0(teamAnimalService.updateTeamAnimalByID(team.getSlot0().getAnimalId(), dto.getSlot0()));
        team.setSlot1(teamAnimalService.updateTeamAnimalByID(team.getSlot1().getAnimalId(), dto.getSlot1()));
        team.setSlot2(teamAnimalService.updateTeamAnimalByID(team.getSlot2().getAnimalId(), dto.getSlot2()));
        team.setSlot3(teamAnimalService.updateTeamAnimalByID(team.getSlot3().getAnimalId(), dto.getSlot3()));
        team.setSlot4(teamAnimalService.updateTeamAnimalByID(team.getSlot4().getAnimalId(), dto.getSlot4()));

        return teamRepo.save(team);
    }

}
