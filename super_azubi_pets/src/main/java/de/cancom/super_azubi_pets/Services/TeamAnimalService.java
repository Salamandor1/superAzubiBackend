package de.cancom.super_azubi_pets.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.cancom.super_azubi_pets.DTOs.CreateAndUpdateTeamAnimalDTO;
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
        TeamAnimal newTeamAnimal = new TeamAnimal();
        newTeamAnimal.setBaseAnimal(baseAnimalService.getAnimalByID(dto.getBaseAnimal().getAnimalName()));
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
    public Optional<TeamAnimal> getTeamAnimalById(Long Id) {
        return teamAnimalRepo.findById(Id);
    }

}
