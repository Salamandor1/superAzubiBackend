package de.cancom.super_azubi_pets.DTOs;

import java.util.ArrayList;

import de.cancom.super_azubi_pets.Models.Animal;

public class CreateAndUpdateTeamDTO {
    private int hearts;
    private int wins;
    private int rounds;

    private ArrayList<Animal> animals;

    public CreateAndUpdateTeamDTO() {
    }

    public CreateAndUpdateTeamDTO(int hearts, int wins, int rounds, ArrayList<Animal> animals) {
        this.hearts = hearts;
        this.wins = wins;
        this.rounds = rounds;
        this.animals = animals;
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

    public void setHearts(int hearts) {
        this.hearts = hearts;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public void setAnimals(ArrayList<Animal> animalIDs) {
        this.animals = animalIDs;
    }
}
