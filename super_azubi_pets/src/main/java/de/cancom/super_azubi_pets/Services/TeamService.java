package de.cancom.super_azubi_pets.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import de.cancom.super_azubi_pets.DTOs.TeamAnimalCreateDTO;
import de.cancom.super_azubi_pets.DTOs.TeamCreateAndUpdateDTO;
import de.cancom.super_azubi_pets.DTOs.TeamResponseDTO;
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
    public Team createTeam(TeamCreateAndUpdateDTO dto) {
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
    public Team updateTeamByID(Long id, TeamCreateAndUpdateDTO dtoTeam) {
        Team team = getTeamByID(id);
        for (int i = 0; i < 5; i++) {
            TeamAnimalCreateDTO dtoAnimal = dtoTeam.getSlotByIndex(i);
            if (dtoAnimal == null) {
                removeAnimal(team, i);
            } else {
                createAnimal(team, i, dtoAnimal);
            }
        }
        return teamRepo.save(team);
    }

    public void removeAnimal(Team team, int i) {
        team.setSlotByIndex(null, i);
    }

    public void createAnimal(Team team, int i, TeamAnimalCreateDTO dto) {
        TeamAnimal animal = new TeamAnimal();
        animal.setBaseAnimal(baseAnimalService.getAnimalByID(dto.getBaseAnimalName()));
        animal.setAttack(dto.getAttack());
        animal.setHealth(dto.getHealth());
        animal.setLevel(dto.getLevel());
        team.setSlotByIndex(animal, i);
    }

    // Delete
    public void deleteTeamByID(Long id) {
        if (teamRepo.existsById(id)) {
            teamRepo.deleteById(id);
        } else
            throw new EntityNotFoundException("Team with ID " + id + " not found");
    }

    // Delete all
    public void deleteAll() {
        teamRepo.deleteAll();
        teamRepo.resetIDSequence();
    }

    // Convert to DTO
    public TeamResponseDTO convertToDTO(Team team) {
        return new TeamResponseDTO(team);
    }

    public List<TeamResponseDTO> convertToDTO(List<Team> teams) {
        List<TeamResponseDTO> response = new ArrayList<>();
        for (Team team : teams) {
            response.add(convertToDTO(team));
        }
        return response;
    }

    // Save method for external use
    public void save(Team team) {
        teamRepo.save(team);
    }

    public boolean areEqual(Team a, Team b) {
        List<TeamAnimal> listA = a.getAllAnimals();
        List<TeamAnimal> listB = b.getAllAnimals();

        if (listA.size() != listB.size()) {
            return false;
        }

        for (int i = 0; i < listA.size(); i++) {
            TeamAnimal animalA = listA.get(i);
            TeamAnimal animalB = listB.get(i);
            if (animalA == animalB) {
                continue;
            }
            if (animalA == null || animalB == null) {
                return false;
            }
            if (animalA.getName() != animalB.getName()) {
                return false;
            }
            if (animalA.getHealth() != animalB.getHealth()) {
                return false;
            }
            if (animalA.getAttack() != animalB.getAttack()) {
                return false;
            }
            if (animalA.getLevel() != animalB.getLevel()) {
                return false;
            }
        }

        return true;
    }

}
