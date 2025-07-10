package de.cancom.super_azubi_pets.DTOs;

public class CreateAndUpdateTeamAnimalDTO {

    private CreateAndUpdateAnimalDTO baseAnimal;
    private int health;
    private int attack;
    private int level;

    public CreateAndUpdateTeamAnimalDTO() {
    }

    public void setBaseAnimal(CreateAndUpdateAnimalDTO baseAnimal) {
        this.baseAnimal = baseAnimal;
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

    public CreateAndUpdateAnimalDTO getBaseAnimal() {
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

}
