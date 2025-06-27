package de.cancom.super_azubi_pets.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.cancom.super_azubi_pets.Models.Animal;
import de.cancom.super_azubi_pets.Repositories.AnimalRepository;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    AnimalRepository repository;

    public ResponseEntity<Animal> getAinmal(@PathVariable Long id) {
        return new ResponseEntity<Animal>(repository.findById(id)
                .orElse(null), HttpStatus.OK);
    }

    public ResponseEntity<Animal> createAnimal(Animal animal) {
        Animal savedAnimal = repository.save(animal);
        return new ResponseEntity<Animal>(savedAnimal, HttpStatus.CREATED);
    }

    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Animal> updateAnimal(@PathVariable Long id, Animal animal) {
        if (!repository.existsById(id)) {
            return new ResponseEntity<Animal>(HttpStatus.NOT_FOUND);
        }
        animal.setAnimalId(id);
        Animal updatedAnimal = repository.save(animal);
        return new ResponseEntity<Animal>(updatedAnimal, HttpStatus.OK);
    }
}
