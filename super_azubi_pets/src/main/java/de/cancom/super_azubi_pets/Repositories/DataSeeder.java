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
        try {
            loadInitialAnimals();
        } catch (Exception e) {

        }
        System.out.println("Daten seeding abgeschlossen.");
    }

    private void loadInitialAnimals() {

        animalRepository.deleteAll();

        List<Animal> standardAnimals = new ArrayList<>();
        // TIER 1: combined value 2 - 4
        standardAnimals.add(new Animal("Faultier", "🦥", 1, 1, 1, "[BLOCK] Reduziert eingehenden Schaden."));
        standardAnimals.add(new Animal("Pferd", "🐴", 2, 2, 1, ""));
        standardAnimals.add(new Animal("Eichhörnchen", "🐿️", 2, 1, 1, ""));
        standardAnimals.add(new Animal("Mücke", "🦟", 3, 1, 1, ""));
        standardAnimals.add(new Animal("Hase", "🐰", 1, 2, 1, ""));

        // TIER 2: combined value 5 - 6
        standardAnimals.add(new Animal("Biber", "🦫", 3, 2, 2, "[BLOCK] Reduziert eingehenden Schaden."));
        standardAnimals.add(new Animal("Rabe", "🐦‍⬛", 3, 2, 2, ""));
        standardAnimals.add(new Animal("Wurm", "🪱", 2, 3, 2, ""));
        standardAnimals.add(new Animal("Fliege", "🪰", 1, 4, 2, ""));
        standardAnimals.add(new Animal("Fisch", "🐟", 2, 4, 2, ""));

        // TIER 3: combined value 7 - 8
        standardAnimals.add(new Animal("Thaddäus", "🦑", 5, 2, 3, "[BLOCK] Reduziert eingehenden Schaden."));
        standardAnimals.add(new Animal("Nilpferd", "🦛", 4, 4, 3, ""));
        standardAnimals.add(new Animal("Kamel", "🐪", 3, 5, 3, ""));
        standardAnimals.add(new Animal("Biene", "🐝", 1, 6, 3, ""));
        standardAnimals.add(new Animal("Ameise", "🐜", 2, 5, 3, ""));

        // TIER 4: combined value 9 - 10
        standardAnimals.add(new Animal("Gorilla", "🦍", 5, 5, 4, "[BLOCK] Reduziert eingehenden Schaden."));
        standardAnimals.add(new Animal("Einhorn", "🦄", 3, 6, 4, ""));
        standardAnimals.add(new Animal("Katze", "🐱", 4, 5, 4, ""));
        standardAnimals.add(new Animal("Stier", "🐃", 2, 7, 4, ""));
        standardAnimals.add(new Animal("Snowy", "⛄", 8, 2, 4, ""));

        // TIER 5: combined value 11 - 12
        standardAnimals.add(new Animal("Schlange", "🐍", 10, 1, 5, "[BLOCK] Reduziert eingehenden Schaden."));
        standardAnimals.add(new Animal("Sauropod", "🦕", 6, 6, 5, ""));
        standardAnimals.add(new Animal("Phönix", "🐦‍🔥", 8, 3, 5, ""));
        standardAnimals.add(new Animal("Kugelfisch", "🐡", 2, 10, 5, ""));
        standardAnimals.add(new Animal("Adler", "🦅", 4, 7, 5, ""));

        // TIER 6: combined value > 12
        standardAnimals.add(new Animal("Elefant", "🐘", 8, 5, 6, "[BLOCK] Reduziert eingehenden Schaden."));
        standardAnimals.add(new Animal("Nashorn", "🦏", 6, 7, 6, ""));
        standardAnimals.add(new Animal("Mammut", "🦣", 7, 7, 6, ""));
        standardAnimals.add(new Animal("Cthulhu", "🐙", 6, 8, 6, ""));
        standardAnimals.add(new Animal("Rex", "🦖", 4, 10, 6, ""));

        for (Animal animal : standardAnimals) {
            animalRepository.save(animal);
        }
    }
}
