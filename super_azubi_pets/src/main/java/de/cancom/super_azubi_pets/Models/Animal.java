package de.cancom.super_azubi_pets.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


/**
 * Represents an animal in the Super Azubi Pets game.
 * This class retrieves animal data from the database based on the provided ID.
 */
@Entity
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long animalId;
    

    private String animalName;
    private int hearts;
    private int attack;
    private String ability;

    public Animal() {
    }

    /**
     * Constructor to create an Animal object with a specific ID.
     * This constructor retrieves the animal's data from the database.
     * @param animalName
     * @param hearts
     * @param attack
     * @param ability
     */
    public Animal(String animalName, int hearts, int attack, String ability) {
        this.animalName = animalName;
        this.hearts = hearts;
        this.attack = attack;
        this.ability = ability;
    }

    //Getters and Setters
    public String getAnimalName(){
        return this.animalName;
    }
    public int getHearts() {
        return this.hearts;
    }
    public int getAttack() {
        return this.attack;
    }
    public String getAbility() {
        return this.ability;
    }
    public Long getAnimalId() {
        return this.animalId;
    }

    
    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }
    public void setHearts(int hearts) {
        this.hearts = hearts;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public void setAbility(String ability) {
        this.ability = ability;
    }
    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }
   

}