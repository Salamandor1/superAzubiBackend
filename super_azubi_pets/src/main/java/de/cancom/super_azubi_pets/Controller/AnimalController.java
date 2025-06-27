package de.cancom.super_azubi_pets.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.cancom.super_azubi_pets.Models.Animal;
import de.cancom.super_azubi_pets.Services.AnimalService;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    // get
    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAinmal(@PathVariable Long id) {
        return new ResponseEntity<Animal>(animalService.getAnimalById(id), HttpStatus.OK);
    }

    // create
    @PostMapping
    public ResponseEntity<Animal> createAnimal(Animal animal) {
        Animal savedAnimal = animalService.createAnimal(animal);
        return new ResponseEntity<Animal>(savedAnimal, HttpStatus.CREATED);
    }

    // delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
        animalService.deleteAnimal(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable Long id, Animal animal) {
        Animal updatedAnimal = animalService.updateAnimal(id, animal);
        return new ResponseEntity<Animal>(updatedAnimal, HttpStatus.OK);
    }
}
