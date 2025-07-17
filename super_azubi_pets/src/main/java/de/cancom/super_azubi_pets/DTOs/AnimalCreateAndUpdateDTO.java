package de.cancom.super_azubi_pets.DTOs;

/**
 * DTO for creating and updating an Animal object.
 * This class contains the necessary fields to create or update an animal's
 * attributes.
 */
public class AnimalCreateAndUpdateDTO {

    private String animalName;
    private int hearts;
    private int attack;
    private AnimalCreateAndUpdateDTO ability;

    public AnimalCreateAndUpdateDTO() {
    }

    /**
     * Constructor to create an Animal object with specific attributes.
     * 
     * @param animalName Name of the animal.
     * @param hearts     Health points of the animal.
     * @param attack     Attack points of the animal.
     * @param ability    Special ability of the animal.
     */
    public AnimalCreateAndUpdateDTO(String animalName, int hearts, int attack,
            AnimalCreateAndUpdateDTO ability) {
        this.animalName = animalName;
        this.hearts = hearts;
        this.attack = attack;
        this.ability = ability;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public int getHearts() {
        return hearts;
    }

    public void setHearts(int hearts) {
        this.hearts = hearts;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public AnimalCreateAndUpdateDTO getAbility() {
        return ability;
    }

    public void setAbility(AnimalCreateAndUpdateDTO ability) {
        this.ability = ability;
    }

}
