package de.cancom.super_azubi_pets.Models;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamID;
    private int hearts;
    private int wins;
    private int rounds;

    private ArrayList<Animal> animals;

    public Team() {
    }

    public Team(int hearts, int wins, int rounds) {
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

    public ArrayList<Animal> getAnimals() {
        return this.animals;
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

    public void setAnimals(ArrayList<Animal> animals) {
        this.animals = animals;
    }

}
