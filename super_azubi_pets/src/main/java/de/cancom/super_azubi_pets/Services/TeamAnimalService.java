package de.cancom.super_azubi_pets.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.cancom.super_azubi_pets.DTOs.CreateAndUpdateTeamAnimalDTO;
import de.cancom.super_azubi_pets.Models.Animal;
import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Repositories.AnimalRepository;
import de.cancom.super_azubi_pets.Repositories.TeamAnimalRepository;

@Service
public class TeamAnimalService {

    @Autowired
    private TeamAnimalRepository teamAnimalRepo;

    @Autowired
    private AnimalRepository baseAnimalRepo;

    // Create
    public TeamAnimal createTeamAnimal(CreateAndUpdateTeamAnimalDTO dto) {
        Animal baseAnimal = baseAnimalRepo.findById(dto.getBaseAnimalID())
                .orElseThrow(() -> new RuntimeException("Base Animal does not exist"));

        TeamAnimal newTeamAnimal = new TeamAnimal(baseAnimal, dto.getLevel(), dto.getPos());
        newTeamAnimal.setHealth(baseAnimal.getHearts());
        newTeamAnimal.setAttack(baseAnimal.getAttack());
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
