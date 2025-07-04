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

    public Animal getAnimalById(String name) {
        return animalRepository.findById(name)
                .orElseThrow(() -> new RuntimeException("Animal with ID " + name + " not found."));
    }

    public Animal createAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public void deleteAnimal(String name) {
        if (!animalRepository.existsById(name)) {
            throw new RuntimeException("Animal with ID " + name + " not found.");
        }
        animalRepository.deleteById(name);
    }

    public Animal updateAnimal(Animal animal) {
        String name = animal.getAnimalName();
        if (!animalRepository.existsById(name)) {
            throw new RuntimeException("Animal with ID " + name + " not found.");
        }
        deleteAnimal(animal.getAnimalName());
        return animalRepository.save(animal);
    }
}
