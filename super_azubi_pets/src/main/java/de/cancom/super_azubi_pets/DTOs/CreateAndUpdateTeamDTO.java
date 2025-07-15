package de.cancom.super_azubi_pets.DTOs;

import java.util.Arrays;
import java.util.List;

public class CreateAndUpdateTeamDTO {
    private CreateAndUpdateTeamAnimalDTO slot0;
    private CreateAndUpdateTeamAnimalDTO slot1;
    private CreateAndUpdateTeamAnimalDTO slot2;
    private CreateAndUpdateTeamAnimalDTO slot3;
    private CreateAndUpdateTeamAnimalDTO slot4;

    public CreateAndUpdateTeamDTO() {
    }

    public CreateAndUpdateTeamAnimalDTO getSlot0() {
        return slot0;
    }

    public CreateAndUpdateTeamAnimalDTO getSlot1() {
        return slot1;
    }

    public CreateAndUpdateTeamAnimalDTO getSlot2() {
        return slot2;
    }

    public CreateAndUpdateTeamAnimalDTO getSlot3() {
        return slot3;
    }

    public CreateAndUpdateTeamAnimalDTO getSlot4() {
        return slot4;
    }

    // getter for flexibility
    public CreateAndUpdateTeamAnimalDTO getSlotByIndex(int i) {
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

    public List<CreateAndUpdateTeamAnimalDTO> getAllSlots() {
        return Arrays.asList(slot0, slot1, slot2, slot3, slot4);
    }

    public void setSlot0(CreateAndUpdateTeamAnimalDTO dto) {
        this.slot0 = dto;
    }

    public void setSlot1(CreateAndUpdateTeamAnimalDTO dto) {
        this.slot1 = dto;
    }

    public void setSlot2(CreateAndUpdateTeamAnimalDTO dto) {
        this.slot2 = dto;
    }

    public void setSlot3(CreateAndUpdateTeamAnimalDTO dto) {
        this.slot3 = dto;
    }

    public void setSlot4(CreateAndUpdateTeamAnimalDTO dto) {
        this.slot4 = dto;
    }
}
