package de.cancom.super_azubi_pets.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Represents an animal which is part of a team and has changed or changable
 * attributes.
 * Every team contains up to five animals, they can be identical but every
 * animal has a different position.
 */
@Entity
@Table(name = "teamanimals")
public class TeamAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamAnimalId;
    private int pos;

    // for references to name and ability
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_id")
    private Animal baseAnimal;

    // these values may differ from the base values, so they are saved separatley
    private int health;
    private int attack;

    // this value is equivalent to xp and is used in a specific EventType to alter
    // health and attack
    private int level;

    public TeamAnimal() {
    }

    public TeamAnimal(Animal baseAnimal, int level, int pos) {
        this.baseAnimal = baseAnimal;
        setPos(pos);
        setHealth(baseAnimal.getHealth());
        setAttack(baseAnimal.getAttack());
        setLevel(level);
    }

    public void setPos(int pos) {
        if (pos < 0 || pos > 4) {
            throw new IllegalArgumentException("Position of animal must be between 0 and 4");
        } else {
            this.pos = pos;
        }
    }

    public void setHealth(int health) {
        this.health = health;
        // health cannot be less than 0
        if (this.health < 0) {
            this.health = 0;
        }
        // health cannot be more than 50
        if (this.health > 50) {
            this.health = 50;
        }
    }

    public void setAttack(int attack) {
        this.attack = attack;
        // attack cannot be less than 1
        if (this.attack < 1) {
            this.attack = 1;
        }
        // attack cannot be more than 50
        if (this.attack > 50) {
            this.attack = 50;
        }
    }

    public void setLevel(int level) {
        this.level = Math.abs(level);
        // level cannot be more than 20
        if (this.level > 20) {
            this.level = 20;
        }
    }

    public Long getAnimalId() {
        return baseAnimal.getAnimalId();
    }

    public int getPos() {
        return pos;
    }

    public String getName() {
        return baseAnimal.getAnimalName();
    }

    public String getAbility() {
        return baseAnimal.getAbility();
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getLevel() {
        return level;
    }

}
