package de.cancom.super_azubi_pets.DTOs;

/**
 * DTO for creating and updating an Animal object.
 * This class contains the necessary fields to create or update an animal's attributes.
 */
public class CreateAndUpdateAnimalDTO {

    private String animalName;
    private int hearts;
    private int attack;
    private String ability;

    public CreateAndUpdateAnimalDTO() {
    }
    /**
     * Constructor to create an Animal object with specific attributes.
     * @param animalName Name of the animal.
     * @param hearts Health points of the animal.
     * @param attack Attack points of the animal.
     * @param ability Special ability of the animal.
     */
    public CreateAndUpdateAnimalDTO(String animalName, int hearts, int attack, String ability) {
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
    public String getAbility() {
        return ability;
    }
    public void setAbility(String ability) {
        this.ability = ability;
    }

}
