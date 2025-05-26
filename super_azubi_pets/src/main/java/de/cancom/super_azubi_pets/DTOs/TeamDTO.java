package de.cancom.super_azubi_pets.DTOs;

public class TeamDTO {
    private Long teamID;
    private int hearts;
    private int wins;
    private int rounds;

    public TeamDTO() {
    }

    public TeamDTO(int hearts, int wins, int rounds) {
        this.hearts = hearts;
        this.wins = wins;
        this.rounds = rounds;
    }

    public Long getTeamID() {
        return this.teamID;
    }

    public int getHearts() {
        return this.hearts;
    }

    public int getWins() {
        return this.wins;
    }

    public int getRounds() {
        return this.rounds;
    }

    // maybe not needed, but could be relevant for testing
    public void setTeamID(Long teamID) {
        this.teamID = teamID;
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
}
