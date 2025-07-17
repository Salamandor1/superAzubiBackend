package de.cancom.super_azubi_pets.DTOs;

public class TeamAnimalUpdateDTO {

    private int level;

    public TeamAnimalUpdateDTO() {
    }

    public TeamAnimalUpdateDTO(int level) {
        setLevel(level);
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

}
