package de.cancom.super_azubi_pets.Models;

import java.util.ArrayList;
import java.util.List;

public class Fight {

    private Game game;

    private Team playerTeam;
    private Team npcTeam;

    private List<TeamAnimal> playerTeamAnimals = new ArrayList<>();
    private List<TeamAnimal> enemyTeamAnimals = new ArrayList<>();

    public Fight() {
    }

    public Fight(Game game) {
        setGame(game);
    }

    public Fight(Team playerTeam, Team npcTeam) {
        this.playerTeam = playerTeam;
        this.npcTeam = npcTeam;
    }

    public void setPlayerTeam(Team playerTeam) {
        this.playerTeam = playerTeam;
    }

    public void setNpcTeam(Team npcTeam) {
        this.npcTeam = npcTeam;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setPlayerTeamAnimals(List<TeamAnimal> playerTeamAnimals) {
        this.playerTeamAnimals = playerTeamAnimals;
    }

    public void setEnemyTeamAnimals(List<TeamAnimal> enemyTeamAnimals) {
        this.enemyTeamAnimals = enemyTeamAnimals;
    }

    public void replacePlayerTeamAnimal(int index, TeamAnimal newAnimal) {
        playerTeamAnimals.set(index, newAnimal);
    }

    public void replaceEnemyTeamAnimal(int index, TeamAnimal newAnimal) {
        enemyTeamAnimals.set(index, newAnimal);
    }

    public Team getPlayerTeam() {
        return playerTeam;
    }

    public Team getNpcTeam() {
        return npcTeam;
    }

    public List<TeamAnimal> getPlayerTeamAnimals() {
        return playerTeamAnimals;
    }

    public List<TeamAnimal> getEnemyTeamAnimals() {
        return enemyTeamAnimals;
    }

    public Game getGame() {
        return game;
    }

}
