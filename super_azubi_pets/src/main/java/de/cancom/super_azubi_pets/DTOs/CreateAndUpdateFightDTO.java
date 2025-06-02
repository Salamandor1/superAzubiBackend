package de.cancom.super_azubi_pets.DTOs;

public class CreateAndUpdateFightDTO {

    private Long team1Id;
    private Long team2Id;

    public Long getTeam1Id() {
        return this.team1Id;
    }

    public Long getTeam2Id() {
        return this.team2Id;
    }

    public void setTeam1Id(Long team1Id) {
        this.team1Id = team1Id;
    }

    public void setTeam2Id(Long team2Id) {
        this.team2Id = team2Id;
    }

}
