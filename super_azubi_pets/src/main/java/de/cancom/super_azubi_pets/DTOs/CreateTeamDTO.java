package de.cancom.super_azubi_pets.DTOs;

public class CreateTeamDTO {
    private int hearts;
    private int wins;
    private int rounds;

    public CreateTeamDTO() {
    }

    public CreateTeamDTO(int hearts, int wins, int rounds) {
        this.hearts = hearts;
        this.wins = wins;
        this.rounds = rounds;
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
