package de.cancom.super_azubi_pets.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import de.cancom.super_azubi_pets.DTOs.TeamAnimalCreateDTO;
import de.cancom.super_azubi_pets.DTOs.TeamAnimalResponseDTO;
import de.cancom.super_azubi_pets.Models.Animal;
import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Repositories.TeamAnimalRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TeamAnimalService {

    @Autowired
    private TeamAnimalRepository teamAnimalRepo;

    @Autowired
    private AnimalService baseAnimalService;

    // Create
    public TeamAnimal createTeamAnimal(TeamAnimalCreateDTO dto) {
        if (dto == null) {
            return null;
        }
        TeamAnimal newTeamAnimal = new TeamAnimal();
        Animal baseAnimal = baseAnimalService.getAnimalByID(dto.getBaseAnimalName());
        newTeamAnimal.setBaseAnimal(baseAnimal);
        newTeamAnimal.setAttack(dto.getAttack());
        newTeamAnimal.setHealth(dto.getHealth());
        newTeamAnimal.setLevel(dto.getLevel());

        return teamAnimalRepo.save(newTeamAnimal);
    }

    // Read
    public List<TeamAnimal> getAllTeamAnimals() {
        return teamAnimalRepo.findAll();
    }

    // Read
    public TeamAnimal getTeamAnimalById(Long Id) {
        return teamAnimalRepo.findById(Id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TeamAnimal not found."));
    }

    // Update
    public TeamAnimal updateTeamAnimalByID(Long id, TeamAnimalCreateDTO dto) {
        TeamAnimal teamAnimal = getTeamAnimalById(id);
        teamAnimal.setBaseAnimal(baseAnimalService.getAnimalByID(dto.getBaseAnimalName()));
        teamAnimal.setAttack(dto.getAttack());
        teamAnimal.setHealth(dto.getHealth());
        teamAnimal.setLevel(dto.getLevel());

        return teamAnimalRepo.save(teamAnimal);
    }

    // Delete
    public void deleteTeamAnimalByID(Long id) {
        if (teamAnimalRepo.existsById(id)) {
            teamAnimalRepo.deleteById(id);
        } else
            throw new EntityNotFoundException("TeamAnimal with id " + id + " not found.");
    }

    // Delete
    public void deleteAllTeamAnimal() {
        teamAnimalRepo.deleteAll();
    }

    // Convert
    public TeamAnimalResponseDTO convertToDTO(TeamAnimal teamAnimal) {
        return new TeamAnimalResponseDTO(teamAnimal);
    }

    public List<TeamAnimalResponseDTO> convertToDTO(List<TeamAnimal> teamAnimals) {
        List<TeamAnimalResponseDTO> response = new ArrayList<>();
        for (TeamAnimal teamanimal : teamAnimals) {
            response.add(convertToDTO(teamanimal));
        }
        return response;
    }

}
