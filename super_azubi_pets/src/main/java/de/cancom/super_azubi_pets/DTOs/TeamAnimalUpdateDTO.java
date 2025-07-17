package de.cancom.super_azubi_pets.DTOs;

public class TeamAnimalUpdateDTO {

    private int health;
    private int attack;
    private int level;

    public TeamAnimalUpdateDTO() {
    }

    public TeamAnimalUpdateDTO(TeamAnimalCreateDTO dto) {
        setHealth(dto.getHealth());
        setAttack(dto.getAttack());
        setLevel(dto.getLevel());
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
