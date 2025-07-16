package de.cancom.super_azubi_pets.DTOs;

import java.util.Arrays;
import java.util.List;

public class TeamCreateAndUpdateDTO {
    private TeamAnimalCreateAndUpdateDTO slot0;
    private TeamAnimalCreateAndUpdateDTO slot1;
    private TeamAnimalCreateAndUpdateDTO slot2;
    private TeamAnimalCreateAndUpdateDTO slot3;
    private TeamAnimalCreateAndUpdateDTO slot4;

    public TeamCreateAndUpdateDTO() {
    }

    public TeamAnimalCreateAndUpdateDTO getSlot0() {
        return slot0;
    }

    public TeamAnimalCreateAndUpdateDTO getSlot1() {
        return slot1;
    }

    public TeamAnimalCreateAndUpdateDTO getSlot2() {
        return slot2;
    }

    public TeamAnimalCreateAndUpdateDTO getSlot3() {
        return slot3;
    }

    public TeamAnimalCreateAndUpdateDTO getSlot4() {
        return slot4;
    }

    // getter for flexibility
    public TeamAnimalCreateAndUpdateDTO getSlotByIndex(int i) {
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

    public List<TeamAnimalCreateAndUpdateDTO> getAllSlots() {
        return Arrays.asList(slot0, slot1, slot2, slot3, slot4);
    }

    public void setSlot0(TeamAnimalCreateAndUpdateDTO dto) {
        this.slot0 = dto;
    }

    public void setSlot1(TeamAnimalCreateAndUpdateDTO dto) {
        this.slot1 = dto;
    }

    public void setSlot2(TeamAnimalCreateAndUpdateDTO dto) {
        this.slot2 = dto;
    }

    public void setSlot3(TeamAnimalCreateAndUpdateDTO dto) {
        this.slot3 = dto;
    }

    public void setSlot4(TeamAnimalCreateAndUpdateDTO dto) {
        this.slot4 = dto;
    }
}
