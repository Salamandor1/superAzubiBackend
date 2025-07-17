package de.cancom.super_azubi_pets.DTOs;

import de.cancom.super_azubi_pets.Models.TeamAnimal;

public class TeamAnimalResponseDTO {

    private Long teamAnimalID;
    private String baseAnimalName;
    private int health;
    private int attack;
    private int level;

    public TeamAnimalResponseDTO() {
    }

    public TeamAnimalResponseDTO(TeamAnimal teamAnimal) {
        setTeamAnimalID(teamAnimal.getAnimalId());
        setBaseAnimalName(teamAnimal.getName());
        setHealth(teamAnimal.getHealth());
        setAttack(teamAnimal.getAttack());
        setLevel(teamAnimal.getLevel());
    }

    public void setTeamAnimalID(Long teamAnimalID) {
        this.teamAnimalID = teamAnimalID;
    }

    public void setBaseAnimalName(String baseAnimalName) {
        this.baseAnimalName = baseAnimalName;
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

    public Long getTeamAnimalID() {
        return teamAnimalID;
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
