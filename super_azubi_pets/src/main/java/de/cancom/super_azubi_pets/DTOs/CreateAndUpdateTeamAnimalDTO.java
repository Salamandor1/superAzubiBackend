package de.cancom.super_azubi_pets.DTOs;

public class CreateAndUpdateTeamAnimalDTO {

    private Long baseAnimalID;
    private int level;
    private int pos;

    public CreateAndUpdateTeamAnimalDTO() {
    }

    public CreateAndUpdateTeamAnimalDTO(Long baseAnimalID, int level, int pos) {
        this.baseAnimalID = baseAnimalID;
        this.level = level;
        this.pos = pos;
    }

    public void setBaseAnimalID(Long baseAnimalID) {
        this.baseAnimalID = baseAnimalID;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public Long getBaseAnimalID() {
        return baseAnimalID;
    }

    public int getLevel() {
        return level;
    }

    public int getPos() {
        return pos;
    }

}
