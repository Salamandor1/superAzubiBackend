package de.cancom.super_azubi_pets.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameID;

    private int hearts;
    private int wins;
    private int rounds;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private GameTeam team;

    public Game() {
    }

    // Constructor for a non empty Game-object with standardized values
    public static Game createGame() {
        Game game = new Game();
        game.hearts = 10;
        game.wins = 0;
        game.rounds = 1;
        game.team = new GameTeam();
        return game;
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

    public int getRound() {
        return rounds;
    }

    public GameTeam getTeam() {
        return team;
    }

    public void setHearts(int hearts) {
        this.hearts = hearts;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setRound(int rounds) {
        this.rounds = rounds;
    }

    public void setTeam(GameTeam team) {
        this.team = team;
    }
}
