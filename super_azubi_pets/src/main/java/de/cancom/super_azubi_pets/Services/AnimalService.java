package de.cancom.super_azubi_pets.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import de.cancom.super_azubi_pets.DTOs.AnimalResponseDTO;
import de.cancom.super_azubi_pets.Models.Animal;
import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Repositories.AnimalRepository;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository baseAnimalRepo;

    // GET by ID
    public Animal getAnimalByID(String nameID) {
        return baseAnimalRepo.findById(nameID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal not found"));
    }

    // GET random by number
    public List<Animal> getRandomAnimals(int round, int count) {
        if (count < 1 || count > 5) {
            throw new IllegalArgumentException("Must be at least 1, can't be more than 5.");
        }
        List<Animal> randomRanimals = new ArrayList<>();
        for (int i = count; i > 0; i--) {
            randomRanimals.add(getRandomAnimal(round));
        }
        return randomRanimals;
    }

    // GET random animal
    public Animal getRandomAnimal(int round) {
        if (round > 11) {
            round = 11;
        }
        int lowestTier = 1;
        int highestTier = 1;
        switch (round) {
            case 11:
                lowestTier++;
                highestTier++;
            case 10, 9:
                highestTier++;
            case 8, 7:
                lowestTier++;
                highestTier++;
            case 6, 5:
                highestTier++;
            case 3:
                highestTier++;
        }
        return baseAnimalRepo.findRandomAnimal(lowestTier, highestTier);
    }

    // GET all
    public List<Animal> getAllAnimals() {
        return baseAnimalRepo.findAll();
    }

    // TRANSFORM to TeamAnimal
    public TeamAnimal transformToTeamAnimal(Animal animal) {
        TeamAnimal teamAnimal = new TeamAnimal(animal, 1);
        return teamAnimal;
    }

    // CONVERT to DTO
    public AnimalResponseDTO convertToDTO(Animal animal) {
        return new AnimalResponseDTO(animal);
    }

    // CONVERT to DTO
    public List<AnimalResponseDTO> convertToDTO(List<Animal> animals) {
        List<AnimalResponseDTO> response = new ArrayList<>();
        for (Animal animal : animals) {
            response.add(new AnimalResponseDTO(animal));
        }
        return response;
    }
}
