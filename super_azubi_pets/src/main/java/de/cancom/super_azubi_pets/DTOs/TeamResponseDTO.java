package de.cancom.super_azubi_pets.DTOs;

import java.util.List;

import de.cancom.super_azubi_pets.Models.Team;
import de.cancom.super_azubi_pets.Models.TeamAnimal;

public class TeamResponseDTO {
    private Long teamID;
    private Long slot0ID;
    private Long slot1ID;
    private Long slot2ID;
    private Long slot3ID;
    private Long slot4ID;

    public TeamResponseDTO() {
    }

    public TeamResponseDTO(Team team) {
        List<TeamAnimal> animals = team.getAllAnimals();
        int i = 0;
        for (TeamAnimal animal : animals) {
            if (animal == null) {
                continue;
            } else {
                setSlotIDByIndex(animal.getAnimalId(), i);
                i++;
            }
        }
    }

    public void setTeamID(Long id) {
        this.teamID = id;
    }

    public void setSlot0ID(Long id) {
        this.slot0ID = id;
    }

    public void setSlot1ID(Long id) {
        this.slot1ID = id;
    }

    public void setSlot2ID(Long id) {
        this.slot2ID = id;
    }

    public void setSlot3ID(Long id) {
        this.slot3ID = id;
    }

    public void setSlot4ID(Long id) {
        this.slot4ID = id;
    }

    public void setSlotIDByIndex(Long id, int index) {
        switch (index) {
            case 0 -> setSlot0ID(id);
            case 1 -> setSlot1ID(id);
            case 2 -> setSlot2ID(id);
            case 3 -> setSlot3ID(id);
            case 4 -> setSlot4ID(id);
            default -> throw new IllegalArgumentException("Index out of bounds: viable index 0-4.");
        }
    }

    public Long getTeamID() {
        return teamID;
    }

    public Long getSlot0ID() {
        return slot0ID;
    }

    public Long setSlot1ID() {
        return slot1ID;
    }

    public Long setSlot2ID() {
        return slot2ID;
    }

    public Long setSlot3ID() {
        return slot3ID;
    }

    public Long setSlot4ID() {
        return slot4ID;
    }

    public Long getSlotIDByIndex(int index) {
        switch (index) {
            case 0:
                return slot0ID;
            case 1:
                return slot1ID;
            case 2:
                return slot2ID;
            case 3:
                return slot3ID;
            case 4:
                return slot4ID;
            default:
                throw new IllegalArgumentException("Index out of bounds: viable index 0-4.");
        }
    }
}
