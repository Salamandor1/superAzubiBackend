package de.cancom.super_azubi_pets.DTOs;

public class GameUpdateDTO {

    private TeamCreateAndUpdateDTO teamDTO;

    public GameUpdateDTO() {
    }

    public void setTeamDTO(TeamCreateAndUpdateDTO teamDTO) {
        this.teamDTO = teamDTO;
    }

    public TeamCreateAndUpdateDTO getTeamDTO() {
        return teamDTO;
    }

}
