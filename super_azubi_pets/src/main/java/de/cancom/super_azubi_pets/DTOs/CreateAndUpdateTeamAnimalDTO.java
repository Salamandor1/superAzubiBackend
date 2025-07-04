package de.cancom.super_azubi_pets.DTOs;

public class CreateAndUpdateTeamAnimalDTO {

    private String baseAnimalID;
    private int level;
    private int pos;

    public CreateAndUpdateTeamAnimalDTO() {
    }

    public CreateAndUpdateTeamAnimalDTO(String baseAnimalID, int level, int pos) {
        this.baseAnimalID = baseAnimalID;
        this.level = level;
        this.pos = pos;
    }

    public void setBaseAnimalID(String baseAnimalID) {
        this.baseAnimalID = baseAnimalID;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getBaseAnimalID() {
        return baseAnimalID;
    }

    public int getLevel() {
        return level;
    }

    public int getPos() {
        return pos;
    }

}
