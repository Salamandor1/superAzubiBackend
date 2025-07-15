package de.cancom.super_azubi_pets.DTOs;

public class GameUpdateDTO {

    private CreateAndUpdateTeamDTO teamDTO;

    public GameUpdateDTO() {
    }

    public void setTeamDTO(CreateAndUpdateTeamDTO teamDTO) {
        this.teamDTO = teamDTO;
    }

    public CreateAndUpdateTeamDTO getTeamDTO() {
        return teamDTO;
    }

}
