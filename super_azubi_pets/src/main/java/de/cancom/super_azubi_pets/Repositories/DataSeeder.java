package de.cancom.super_azubi_pets.Repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import de.cancom.super_azubi_pets.Models.Animal;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Starte Daten seeding der Tiere.");
        loadInitialAnimals();
        System.out.println("Daten seeding abgeschlossen.");
    }

    private void loadInitialAnimals() {

        List<Animal> standardAnimals = new ArrayList<>();
        standardAnimals.add(new Animal("Pferd", 2, 2, ""));
        standardAnimals.add(new Animal("Thaddäus", 5, 2, ""));
        standardAnimals.add(new Animal("Elefant", 6, 7, ""));
        standardAnimals.add(new Animal("Nilpferd", 4, 4, ""));
        standardAnimals.add(new Animal("Gorilla", 5, 5, ""));
        standardAnimals.add(new Animal("Einhorn", 2, 3, ""));
        standardAnimals.add(new Animal("Eichhörnchen", 2, 1, ""));
        standardAnimals.add(new Animal("Biber", 3, 2, ""));
        standardAnimals.add(new Animal("Faultier", 1, 1, ""));
        standardAnimals.add(new Animal("Rabe", 3, 2, ""));
        standardAnimals.add(new Animal("Katze", 3, 4, ""));
        standardAnimals.add(new Animal("Schlange", 10, 1, ""));
        standardAnimals.add(new Animal("Kamel", 3, 5, ""));
        standardAnimals.add(new Animal("Nashorn", 7, 5, ""));
        standardAnimals.add(new Animal("Wurm", 2, 3, ""));
        standardAnimals.add(new Animal("Ameise", 3, 3, ""));
        standardAnimals.add(new Animal("Fliege", 1, 4, ""));
        standardAnimals.add(new Animal("Mücke", 3, 1, ""));
        standardAnimals.add(new Animal("Biene", 5, 2, ""));
        standardAnimals.add(new Animal("Fisch", 2, 4, ""));

        for (int i = 0; i < standardAnimals.size(); i++) {
            long animalid = i + 1;

            Animal currentAnimal = standardAnimals.get(i);
            currentAnimal.setAnimalId(animalid);
            animalRepository.save(currentAnimal);
        }
    }
}
