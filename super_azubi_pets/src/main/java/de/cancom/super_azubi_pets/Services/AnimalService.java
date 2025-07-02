package de.cancom.super_azubi_pets.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Animal createAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public void deleteAnimal(Long id) {
        if (!animalRepository.existsById(id)) {
            throw new RuntimeException("Animal with ID " + id + " not found.");
        }
        animalRepository.deleteById(id);
    }

    public Animal updateAnimal(Long id, Animal animal) {
        if (!animalRepository.existsById(id)) {
            throw new RuntimeException("Animal with ID " + id + " not found.");
        }
        animal.setAnimalId(id);
        return animalRepository.save(animal);
    }
}
