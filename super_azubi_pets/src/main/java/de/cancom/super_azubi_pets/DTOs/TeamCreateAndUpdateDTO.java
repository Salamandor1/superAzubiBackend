package de.cancom.super_azubi_pets.DTOs;

import java.util.Arrays;
import java.util.List;

public class TeamCreateAndUpdateDTO {
    private TeamAnimalCreateDTO slot0;
    private TeamAnimalCreateDTO slot1;
    private TeamAnimalCreateDTO slot2;
    private TeamAnimalCreateDTO slot3;
    private TeamAnimalCreateDTO slot4;

    public TeamCreateAndUpdateDTO() {
    }

    public TeamAnimalCreateDTO getSlot0() {
        return slot0;
    }

    public TeamAnimalCreateDTO getSlot1() {
        return slot1;
    }

    public TeamAnimalCreateDTO getSlot2() {
        return slot2;
    }

    public TeamAnimalCreateDTO getSlot3() {
        return slot3;
    }

    public TeamAnimalCreateDTO getSlot4() {
        return slot4;
    }

    // getter for flexibility
    public TeamAnimalCreateDTO getSlotByIndex(int i) {
        switch (i) {
            case 0:
                return getSlot0();
            case 1:
                return getSlot1();
            case 2:
                return getSlot2();
            case 3:
                return getSlot3();
            case 4:
                return getSlot4();
            default:
                throw new IllegalArgumentException("Index out of bounds: possible index 0 - 4");
        }
    }

    public List<TeamAnimalCreateDTO> getAllSlots() {
        return Arrays.asList(slot0, slot1, slot2, slot3, slot4);
    }

    public void setSlot0(TeamAnimalCreateDTO dto) {
        this.slot0 = dto;
    }

    public void setSlot1(TeamAnimalCreateDTO dto) {
        this.slot1 = dto;
    }

    public void setSlot2(TeamAnimalCreateDTO dto) {
        this.slot2 = dto;
    }

    public void setSlot3(TeamAnimalCreateDTO dto) {
        this.slot3 = dto;
    }

    public void setSlot4(TeamAnimalCreateDTO dto) {
        this.slot4 = dto;
    }
}
