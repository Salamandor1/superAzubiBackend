package de.cancom.super_azubi_pets.Models;

import de.cancom.super_azubi_pets.Models.Skills.Skill;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 * Represents an animal which is part of a team and has changed or changable
 * attributes.
 * Every team contains up to five animals, they can be identical but every
 * animal has a different position.
 */
@Entity
@Table(name = "team_animals")
public class TeamAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long team_animal_id;

    // for references to name and ability
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "base_animal_id", referencedColumnName = "name")
    private Animal baseAnimal;

    // these values may differ from the base values, so they are saved separatley
    private int health;
    private int attack;

    // this value is equivalent to xp
    private int level;

    @Transient
    private Skill skill;

    public TeamAnimal() {
    }

    public TeamAnimal(Animal baseAnimal, int level) {
        this.baseAnimal = baseAnimal;
        setHealth(baseAnimal.getHealth());
        setAttack(baseAnimal.getAttack());
        setLevel(level);
    }

    public TeamAnimal(TeamAnimal original) {
        this.baseAnimal = original.getBaseAnimal();
        this.health = original.getHealth();
        this.attack = original.getAttack();
        this.level = original.getLevel();
        this.skill = original.getSkill();
    }

    public void setTeamAnimalID(Long id) {
        this.team_animal_id = id;
    }

    public void setBaseAnimal(Animal baseAnimal) {
        this.baseAnimal = baseAnimal;
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

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public void levelUp() {
        if (this.level > 19) {
            return;
        }
        this.level++;

        int addHealth = (int) (Math.round(this.health * 0.15));
        if (addHealth > 5) {
            addHealth = 5;
        }
        if (addHealth < 1) {
            addHealth = 1;
        }
        setHealth(this.health + addHealth);

        int addAttack = 1;
        addAttack = (int) (Math.round(this.attack * 0.15));
        if (addAttack > 5) {
            addAttack = 5;
        }
        if (addAttack < 1) {
            addAttack = 1;
        }
        setAttack(this.attack + addAttack);
    }

    public Long getAnimalId() {
        return team_animal_id;
    }

    public String getName() {
        return baseAnimal.getName();
    }

    public String getEmoji() {
        return baseAnimal.getEmoji();
    }

    public int getTier() {
        return baseAnimal.getTier();
    }

    public String getSkillDescription() {
        return baseAnimal.getSkill();
    }

    public Animal getBaseAnimal() {
        return baseAnimal;
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

    public Skill getSkill() {
        return skill;
    }

}
