package de.cancom.super_azubi_pets.DTOs;

public class GameCreateDTO {
    private int hearts;
    private int wins;
    private int rounds;

    public GameCreateDTO() {
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
