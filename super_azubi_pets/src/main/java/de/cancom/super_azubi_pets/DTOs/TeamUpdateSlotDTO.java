package de.cancom.super_azubi_pets.DTOs;

public class TeamUpdateSlotDTO {
    private CreateAndUpdateTeamAnimalDTO dto;
    private int index;

    public TeamUpdateSlotDTO() {
    }

    public TeamUpdateSlotDTO(CreateAndUpdateTeamAnimalDTO dto, int index) {
        setUpdateDTO(dto);
        setIndex(index);
    }

    public void setUpdateDTO(CreateAndUpdateTeamAnimalDTO dto) {
        this.dto = dto;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public CreateAndUpdateTeamAnimalDTO getTeamAnimalDTO() {
        return dto;
    }

    public int getIndex() {
        return index;
    }
}
