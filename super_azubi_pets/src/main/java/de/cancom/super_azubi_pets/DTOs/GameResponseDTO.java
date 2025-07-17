package de.cancom.super_azubi_pets.DTOs;

import de.cancom.super_azubi_pets.Models.Game;

public class GameResponseDTO {

    private Long gameID;
    private int hearts;
    private int wins;
    private int rounds;
    private Long teamID;

    public GameResponseDTO() {
    }

    public GameResponseDTO(Game game) {
        setGameID(game.getGameID());
        setHearts(game.getHearts());
        setWins(game.getWins());
        setRounds(game.getRounds());
        setTeamID(game.getTeam().getID());
    }

    public void setGameID(Long gameID) {
        this.gameID = gameID;
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

    public void setTeamID(Long teamID) {
        this.teamID = teamID;
    }

    public Long getGameID() {
        return gameID;
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

    public Long getTeamID() {
        return teamID;
    }

}
