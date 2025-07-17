package de.cancom.super_azubi_pets.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.cancom.super_azubi_pets.DTOs.AnimalResponseDTO;
import de.cancom.super_azubi_pets.Models.Animal;
import de.cancom.super_azubi_pets.Services.AnimalService;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    // GET by ID
    @GetMapping("/{name}")
    public ResponseEntity<AnimalResponseDTO> getAninmal(@PathVariable String name) {
        Animal animal = animalService.getAnimalByID(name);
        return ResponseEntity.ok(animalService.convertToDTO(animal));
    }

    // GET random amount
    @GetMapping("/random/{count}")
    public ResponseEntity<List<AnimalResponseDTO>> getRandomAnimals(@PathVariable int count) {
        List<Animal> animals = animalService.getRandomAnimals(count);
        return ResponseEntity.ok(animalService.convertToDTO(animals));
    }

    // GET all
    @GetMapping
    public ResponseEntity<List<AnimalResponseDTO>> getAllAnimals() {
        List<Animal> animals = animalService.getAllAnimals();
        return ResponseEntity.ok(animalService.convertToDTO(animals));
    }

}
