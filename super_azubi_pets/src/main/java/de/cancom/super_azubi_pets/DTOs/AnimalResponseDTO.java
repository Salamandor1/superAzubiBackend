package de.cancom.super_azubi_pets.DTOs;

import de.cancom.super_azubi_pets.Models.Animal;

/**
 * DTO for creating and updating an Animal object.
 * This class contains the necessary fields to create or update an animal's
 * attributes.
 */
public class AnimalResponseDTO {

    private String animalName;
    private int health;
    private int attack;
    private String ability;

    public AnimalResponseDTO() {
    }

    public AnimalResponseDTO(Animal animal) {
        setAnimalName(animal.getName());
        setHealth(animal.getHealth());
        setAttack(animal.getAttack());
        setAbility(animal.getAbility());
    }

    /**
     * Constructor to create an Animal object with specific attributes.
     * 
     * @param animalName Name of the animal.
     * @param health     Health points of the animal.
     * @param attack     Attack points of the animal.
     * @param ability    Special ability of the animal.
     */
    public AnimalResponseDTO(String animalName, int health, int attack,
            String ability) {
        this.animalName = animalName;
        this.health = health;
        this.attack = attack;
        this.ability = ability;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

}
