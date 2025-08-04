package de.cancom.super_azubi_pets.Repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import de.cancom.super_azubi_pets.Models.Animal;
import de.cancom.super_azubi_pets.Models.Game;
import de.cancom.super_azubi_pets.Services.GameService;
import de.cancom.super_azubi_pets.Services.LogService;
import de.cancom.super_azubi_pets.Services.TeamAnimalService;
import de.cancom.super_azubi_pets.Services.TeamService;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private GameService gameService;

    @Autowired
    private LogService logService;

    @Autowired
    private TeamAnimalService teamAnimalService;

    @Autowired
    private TeamService teamService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Starte Daten seeding der Tiere.");
        try {
            List<Game> savedGames = saveGames();
            deleteDatabase();
            loadInitialAnimals();
            refeedGames(savedGames);
            System.out.println("Daten seeding abgeschlossen.");
        } catch (Exception e) {
            System.out.println("Daten seeding abgebrochen.");
            e.printStackTrace();
        }

    }

    private List<Game> saveGames() {
        List<Game> oldGames = gameService.fetchAllGames();
        List<Game> newGames = refreshGames(oldGames);
        return newGames;
    }

    private List<Game> refreshGames(List<Game> oldGames) {
        List<Game> newGames = new ArrayList<>();
        for (Game game : oldGames) {
            newGames.add(new Game(game));
        }
        return newGames;
    }

    private void deleteDatabase() {
        gameService.deleteAllGames();
        logService.deleteAll();
        teamService.deleteAll();
        teamAnimalService.deleteAllTeamAnimal();
        animalRepository.deleteAll();
    }

    private void loadInitialAnimals() {

        /*
         * Skill:
         * Apprentice = [LEHRLING]
         * Block = [BLOCK]
         * Courage = [MUT]
         * Guardian = [BESCHÜTZER]
         * Rage = [RAGE]
         * Revenge = [RACHE]
         * Shield = [SCHILD]
         * Hide = [VERSTECKEN]
         * Thorns = [DORNEN]
         * String = [STICH]
         */

        List<Animal> standardAnimals = new ArrayList<>();
        // TIER 1: combined value 2 - 4
        standardAnimals.add(new Animal("Faultier", "🦥", 1, 1, 1, "[MUT]"));
        standardAnimals.add(new Animal("Pferd", "🐴", 2, 2, 1, "[NONE]"));
        standardAnimals.add(new Animal("Eichhörnchen", "🐿️", 2, 1, 1, "[NONE]"));
        standardAnimals.add(new Animal("Mücke", "🦟", 3, 1, 1, "[STICH]"));
        standardAnimals.add(new Animal("Hase", "🐰", 1, 2, 1, "[VERSTECKEN] "));
        standardAnimals.add(new Animal("Pfau", "🦚", 1, 3, 1, "[NONE]"));

        // TIER 2: combined value 5 - 6
        standardAnimals.add(new Animal("Biber", "🦫", 3, 2, 2, "[BLOCK]"));
        standardAnimals.add(new Animal("Rabe", "🐦‍⬛", 3, 2, 2, "[LEHRLING]"));
        standardAnimals.add(new Animal("Wurm", "🪱", 2, 3, 2, "[NONE]"));
        standardAnimals.add(new Animal("Fliege", "🪰", 1, 4, 2, "[NONE]"));
        standardAnimals.add(new Animal("Fisch", "🐟", 2, 4, 2, "[NONE]"));
        standardAnimals.add(new Animal("Igel", "🦔", 4, 1, 2, "[DORNEN]"));
        standardAnimals.add(new Animal("Frosch", "🐸", 3, 1, 2, "[NONE]"));

        // TIER 3: combined value 7 - 8
        standardAnimals.add(new Animal("Thaddäus", "🦑", 5, 2, 3, "[MUT]"));
        standardAnimals.add(new Animal("Nilpferd", "🦛", 4, 4, 3, "[BLOCK]"));
        standardAnimals.add(new Animal("Kamel", "🐪", 3, 5, 3, "[SCHILD]"));
        standardAnimals.add(new Animal("Biene", "🐝", 1, 6, 3, "[BESCHÜTZER]"));
        standardAnimals.add(new Animal("Ameise", "🐜", 2, 5, 3, "[RAGE]"));
        standardAnimals.add(new Animal("Wal", "🐋", 6, 1, 3, "[NONE]"));
        standardAnimals.add(new Animal("Gute Fee", "🧚‍♀️", 4, 3, 3, "[VERSTECKEN]"));
        standardAnimals.add(new Animal("Spinne", "🕷️", 2, 6, 3, "[NONE]"));

        // TIER 4: combined value 9 - 10
        standardAnimals.add(new Animal("Gorilla", "🦍", 5, 5, 4, "[RAGE]"));
        standardAnimals.add(new Animal("Einhorn", "🦄", 3, 6, 4, "[BESCHÜTZER]"));
        standardAnimals.add(new Animal("Katze", "🐱", 4, 5, 4, "[LEHRLING]"));
        standardAnimals.add(new Animal("Stier", "🐃", 2, 7, 4, "[RACHE]"));
        standardAnimals.add(new Animal("Snowy", "⛄", 8, 2, 4, "[SCHILD]"));
        standardAnimals.add(new Animal("Skorpion", "🦂", 3, 7, 4, "[STICH]"));
        standardAnimals.add(new Animal("Zombie", "🧟‍♂️", 4, 6, 4, "[NONE]"));

        // TIER 5: combined value 11 - 12
        standardAnimals.add(new Animal("Schlange", "🐍", 10, 1, 5, "[SCHILD]"));
        standardAnimals.add(new Animal("Sauropod", "🦕", 6, 6, 5, "[BLOCK]"));
        standardAnimals.add(new Animal("Phönix", "🐦‍🔥", 8, 3, 5, "[BESCHÜTZER]"));
        standardAnimals.add(new Animal("Kugelfisch", "🐡", 2, 10, 5, "[RACHE]"));
        standardAnimals.add(new Animal("Adler", "🦅", 4, 7, 5, "[LEHRLING]"));
        standardAnimals.add(new Animal("Geist", "👻", 10, 2, 5, "[NONE]"));
        standardAnimals.add(new Animal("Vampir", "🧛", 6, 5, 5, "[NONE]"));

        // TIER 6: combined value 13 - 14
        standardAnimals.add(new Animal("Elefant", "🐘", 8, 5, 6, "[LEHRLING]"));
        standardAnimals.add(new Animal("Nashorn", "🦏", 6, 7, 6, "[RACHE]"));
        standardAnimals.add(new Animal("Mammut", "🦣", 7, 7, 6, "[BLOCK]"));
        standardAnimals.add(new Animal("Cthulhu", "🐙", 6, 8, 6, "[MUT]"));
        standardAnimals.add(new Animal("Rex", "🦖", 4, 10, 6, "[RAGE]"));
        standardAnimals.add(new Animal("Alien", "👽", 10, 4, 6, "[STICH]"));
        standardAnimals.add(new Animal("Oger", "🧌", 5, 9, 6, "[NONE]"));

        // TIER 7: combined value > 14
        standardAnimals.add(new Animal("Goekdeniz", "🤡", 15, 1, 7, "[VERSTECKEN]"));
        standardAnimals.add(new Animal("Joshua", "🤑", 1, 15, 7, "[NONE]"));
        standardAnimals.add(new Animal("Matthias", "😎", 10, 6, 7, "[NONE]"));
        standardAnimals.add(new Animal("Meryem", "🧠", 6, 10, 7, "[NONE]"));
        standardAnimals.add(new Animal("Tobias", "💩", 8, 8, 7, "[DORNEN]"));

        for (Animal animal : standardAnimals) {
            animalRepository.save(animal);
        }
    }

    private void refeedGames(List<Game> games) {
        for (Game game : games) {
            gameService.saveGame(game);
        }
    }
}
