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
    private Long game_ID;

    private int hearts;
    private int wins;
    private int rounds;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id", referencedColumnName = "team_id")
    private Team team;

    public Game() {
    }

    public Game(Game game) {
        this.hearts = game.hearts;
        this.rounds = game.hearts;
        this.wins = game.wins;
        this.team = new Team(game.team);
    }

    public Long getGameID() {
        return game_ID;
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

    public Team getTeam() {
        return team;
    }

    public void setHearts(int hearts) {
        this.hearts = hearts;
        if (this.hearts < 0) {
            this.hearts = 0;
        }
        // max value
        if (this.hearts > 15) {
            this.hearts = 15;
        }
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setRound(int rounds) {
        this.rounds = rounds;
        // min value
        if (this.rounds < 1) {
            this.rounds = 1;
        }
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
