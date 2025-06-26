package de.cancom.super_azubi_pets.Services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import de.cancom.super_azubi_pets.Models.Animal;
import de.cancom.super_azubi_pets.Repositories.AnimalRepository;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public Animal getAnimalById(Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal with ID " + id + " not found."));
    }
}
