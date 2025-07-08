package de.cancom.super_azubi_pets.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @GetMapping("/{name}")
    public ResponseEntity<?> getAninmal(@PathVariable String name) {
        try {
            return new ResponseEntity<Animal>(animalService.getAnimalById(name), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Das Tier gibts nicht, du Fisch.", HttpStatus.BAD_REQUEST);
        }
    }

    // create
    @PostMapping
    public ResponseEntity<?> createAnimal(@RequestBody Animal animal) {
        try {
            Animal savedAnimal = animalService.createAnimal(animal);
            return new ResponseEntity<Animal>(savedAnimal, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Das Tier konnte nicht geboren werden :( *sad noises*",
                    HttpStatus.BAD_REQUEST);
        }
    }

    // delete
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<?> deleteAnimal(@PathVariable String name) {
        try {
            animalService.deleteAnimal(name);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<String>("Das Tier gibts nicht, kann also nicht gelöscht werden",
                    HttpStatus.BAD_REQUEST);
        }
    }

    // update
    @PutMapping("/{name}")
    public ResponseEntity<?> updateAnimal(@RequestBody Animal animal) {
        try {
            Animal updatedAnimal = animalService.updateAnimal(animal);
            return new ResponseEntity<Animal>(updatedAnimal, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Das Tier mag seine alten Daten lieber", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/random")
    public ResponseEntity<?> getFiveRandomAnimals() {
        try {
            return new ResponseEntity<>(animalService.getFiveRandomAnimals(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Konnte keine zufälligen Tiere laden", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
