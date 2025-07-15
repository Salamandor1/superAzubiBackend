package de.cancom.super_azubi_pets.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import de.cancom.super_azubi_pets.DTOs.CreateAndUpdateTeamAnimalDTO;
import de.cancom.super_azubi_pets.Models.Animal;
import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Repositories.TeamAnimalRepository;

@Service
public class TeamAnimalService {

    @Autowired
    private TeamAnimalRepository teamAnimalRepo;

    @Autowired
    private AnimalService baseAnimalService;

    // Create
    public TeamAnimal createTeamAnimal(CreateAndUpdateTeamAnimalDTO dto) {
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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TeamAnimal not found"));
    }

    // Update
    public TeamAnimal updateTeamAnimalByID(Long id, CreateAndUpdateTeamAnimalDTO dto) {
        TeamAnimal teamAnimal = getTeamAnimalById(id);
        teamAnimal.setBaseAnimal(baseAnimalService.getAnimalByID(dto.getBaseAnimalName()));
        teamAnimal.setAttack(dto.getAttack());
        teamAnimal.setHealth(dto.getHealth());
        teamAnimal.setLevel(dto.getLevel());

        return teamAnimalRepo.save(teamAnimal);
    }

}
