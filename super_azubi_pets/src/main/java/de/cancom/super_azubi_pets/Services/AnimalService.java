package de.cancom.super_azubi_pets.Services;

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

    // Get
    public Animal getAnimalByID(String nameID) {
        return baseAnimalRepo.findById(nameID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal not found"));
    }
}
