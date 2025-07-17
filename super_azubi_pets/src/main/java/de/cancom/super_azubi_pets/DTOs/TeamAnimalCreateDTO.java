package de.cancom.super_azubi_pets.DTOs;

public class TeamAnimalCreateDTO {

    private String baseAnimalName;
    private int health;
    private int attack;
    private int level;

    public TeamAnimalCreateDTO() {
    }

    public void setBaseAnimalName(String baseAnimal) {
        this.baseAnimalName = baseAnimal;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getBaseAnimalName() {
        return baseAnimalName;
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
