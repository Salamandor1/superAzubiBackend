package de.cancom.super_azubi_pets.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import de.cancom.super_azubi_pets.DTOs.CreateAndUpdateTeamAnimalDTO;
import de.cancom.super_azubi_pets.DTOs.CreateAndUpdateTeamDTO;
import de.cancom.super_azubi_pets.Models.Team;
import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Repositories.TeamRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepo;

    @Autowired
    private TeamAnimalService teamAnimalService;

    @Autowired
    private AnimalService baseAnimalService;

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

    public Team createTeam() {
        Team team = new Team();
        team.setSlot0(null);
        team.setSlot1(null);
        team.setSlot2(null);
        team.setSlot3(null);
        team.setSlot4(null);
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
    public Team updateTeamByID(Long id, CreateAndUpdateTeamDTO dtoTeam) {
        Team team = getTeamByID(id);
        for (int i = 0; i < 5; i++) {
            CreateAndUpdateTeamAnimalDTO dtoAnimal = dtoTeam.getSlotByIndex(i);
            TeamAnimal currentAnimal = team.getSlotByIndex(i);
            if (dtoAnimal == null) {
                removeAnimal(team, i);
            } else if (currentAnimal == null) {
                createAnimal(team, i, dtoAnimal);
            } else {
                updateAnimal(team, i, dtoAnimal);
            }
        }
        return teamRepo.save(team);
    }

    public void removeAnimal(Team team, int i) {
        team.setSlotByIndex(null, i);
    }

    public void createAnimal(Team team, int i, CreateAndUpdateTeamAnimalDTO dto) {
        TeamAnimal animal = new TeamAnimal();
        animal.setBaseAnimal(baseAnimalService.getAnimalByID(dto.getBaseAnimalName()));
        animal.setAttack(dto.getAttack());
        animal.setHealth(dto.getHealth());
        animal.setLevel(dto.getLevel());
        team.setSlotByIndex(animal, i);
    }

    public void updateAnimal(Team team, int i, CreateAndUpdateTeamAnimalDTO dto) {
        team.setSlotByIndex(teamAnimalService.updateTeamAnimalByID(team.getSlotByIndex(i).getAnimalId(), dto), i);
    }

    // Delete
    public void deleteTeamByID(Long id) {
        if (teamRepo.existsById(id)) {
            teamRepo.deleteById(id);
        } else
            throw new EntityNotFoundException("Team with ID " + id + " not found");
    }

}
