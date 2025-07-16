package de.cancom.super_azubi_pets.DTOs;

public class TeamUpdateSlotDTO {
    private TeamAnimalCreateAndUpdateDTO dto;
    private int index;

    public TeamUpdateSlotDTO() {
    }

    public TeamUpdateSlotDTO(TeamAnimalCreateAndUpdateDTO dto, int index) {
        setDTO(dto);
        setIndex(index);
    }

    public void setDTO(TeamAnimalCreateAndUpdateDTO dto) {
        this.dto = dto;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public TeamAnimalCreateAndUpdateDTO getDTO() {
        return dto;
    }

    public int getIndex() {
        return index;
    }
}
