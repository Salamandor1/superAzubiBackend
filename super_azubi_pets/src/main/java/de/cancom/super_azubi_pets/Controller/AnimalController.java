package de.cancom.super_azubi_pets.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.cancom.super_azubi_pets.Models.Animal;
import de.cancom.super_azubi_pets.Services.AnimalService;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    // GET by ID
    @GetMapping("/{name}")
    public ResponseEntity<?> getAninmal(@PathVariable String name) {
        try {
            return new ResponseEntity<Animal>(animalService.getAnimalByID(name), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Das Tier gibts nicht, du Fisch.", HttpStatus.BAD_REQUEST);
        }
    }

    // GET random amount
    @GetMapping("/random/{count}")
    public ResponseEntity<?> getRandomAnimals(@PathVariable int count) {
        try {
            return new ResponseEntity<>(animalService.getRandomAnimals(count), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Konnte keine zufälligen Tiere laden", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET all
    @GetMapping
    public ResponseEntity<?> getAllAnimals() {
        try {
            return new ResponseEntity<>(animalService.getAllAnimals(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Tiere konnten nicht geladen werden", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
