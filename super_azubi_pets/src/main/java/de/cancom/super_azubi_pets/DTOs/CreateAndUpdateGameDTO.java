package de.cancom.super_azubi_pets.DTOs;

public class CreateAndUpdateGameDTO {
    private int hearts;
    private int wins;
    private int rounds;

    private CreateAndUpdateGameTeamDTO teamDTO;

    public CreateAndUpdateGameDTO() {
    }

    public int getHearts() {
        return hearts;
    }

    public int getWins() {
        return wins;
    }

    public int getRounds() {
        return rounds;
    }

    public CreateAndUpdateGameTeamDTO getTeamDTO() {
        return teamDTO;
    }

    public void setHearts(int hearts) {
        this.hearts = hearts;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public void setTeam(CreateAndUpdateGameTeamDTO teamDTO) {
        this.teamDTO = teamDTO;
    }

}
