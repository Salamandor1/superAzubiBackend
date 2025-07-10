package de.cancom.super_azubi_pets.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import de.cancom.super_azubi_pets.Models.Animal;
import de.cancom.super_azubi_pets.Repositories.AnimalRepository;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository baseAnimalRepo;

    // GET
    public Animal getAnimalByID(String nameID) {
        return baseAnimalRepo.findById(nameID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal not found"));
    }

    // GET random by number
    public List<Animal> getRandomAnimals(int count) {
        if (count < 1 || count > 5) {
            throw new IllegalArgumentException("Must be at least 1, can't be more than 5.");
        }
        return baseAnimalRepo.findRandomAnimals(count);
    }
}
