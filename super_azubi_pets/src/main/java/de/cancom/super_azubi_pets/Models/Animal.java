package de.cancom.super_azubi_pets.Models;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents an animal in the Super Azubi Pets game.
 * This class retrieves animal data from the database based on the provided ID.
 */
@Entity
@Immutable
@Table(name = "animals")
public class Animal {

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;

    private int health;
    private int attack;
    private String ability;

    public Animal() {
    }

    /**
     * Constructor to create an Animal object with a specific ID.
     * This constructor retrieves the animal's data from the database.
     * 
     * @param name
     * @param health
     * @param attack
     * @param ability
     */
    public Animal(String name, int health, int attack, String ability) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.ability = ability;
    }

    // Getters and Setters
    public String getName() {
        return this.name;
    }

    public int getHealth() {
        return this.health;
    }

    public int getAttack() {
        return this.attack;
    }

    public String getAbility() {
        return this.ability;
    }

    // no Setter because immutable
}