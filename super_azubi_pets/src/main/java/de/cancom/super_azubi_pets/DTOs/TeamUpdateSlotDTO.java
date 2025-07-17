package de.cancom.super_azubi_pets.DTOs;

public class TeamUpdateSlotDTO {
    private TeamAnimalCreateDTO dto;
    private int index;

    public TeamUpdateSlotDTO() {
    }

    public TeamUpdateSlotDTO(TeamAnimalCreateDTO dto, int index) {
        setDTO(dto);
        setIndex(index);
    }

    public void setDTO(TeamAnimalCreateDTO dto) {
        this.dto = dto;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public TeamAnimalCreateDTO getDTO() {
        return dto;
    }

    public int getIndex() {
        return index;
    }
}
