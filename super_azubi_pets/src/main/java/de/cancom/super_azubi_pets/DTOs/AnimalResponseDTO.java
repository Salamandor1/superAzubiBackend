package de.cancom.super_azubi_pets.DTOs;

import de.cancom.super_azubi_pets.Models.Animal;

/**
 * DTO for creating and updating an Animal object.
 * This class contains the necessary fields to create or update an animal's
 * attributes.
 */
public class AnimalResponseDTO {

    private String name;
    private String emoji;
    private int health;
    private int attack;
    private int tier;
    private String skill;

    public AnimalResponseDTO() {
    }

    public AnimalResponseDTO(Animal animal) {
        setName(animal.getName());
        setEmoji(animal.getEmoji());
        setHealth(animal.getHealth());
        setAttack(animal.getAttack());
        setTier(animal.getTier());
        setSkill(animal.getSkill());
    }

    /**
     * Constructor to create an Animal object with specific attributes.
     * 
     * @param animalName Name of the animal.
     * @param health     Health points of the animal.
     * @param attack     Attack points of the animal.
     * @param skill    Special ability of the animal.
     */
    public AnimalResponseDTO(String animalName, int health, int attack,
            String skill) {
        this.name = animalName;
        this.health = health;
        this.attack = attack;
        this.skill = skill;
    }

    public String getName() {
        return name;
    }

    public void setName(String animalName) {
        this.name = animalName;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
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

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String ability) {
        this.skill = ability;
    }

}
