package de.cancom.super_azubi_pets.Models.Skills;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.cancom.super_azubi_pets.Models.Fight;
import de.cancom.super_azubi_pets.Models.Game;
import de.cancom.super_azubi_pets.Models.TeamAnimal;

public class FightState {
    private Game game;
    private List<TeamAnimal> playerTeam;
    private List<TeamAnimal> enemyTeam;
    private int incomingDmg;
    private int outgoingDmg;
    private String log;
    private Map<TeamAnimal, TeamAnimal> copyToOrigin = new HashMap<>();

    public FightState(Game game, List<TeamAnimal> playerTeam, List<TeamAnimal> enemyTeam) {
        setGame(game);
        setPlayerTeam(playerTeam);
        setEnemyTeam(enemyTeam);
        setIncomingDmg(0);
        setOutgoingDmg(0);
        setLog("");
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setPlayerTeam(List<TeamAnimal> playerTeam) {
        this.playerTeam = playerTeam;
    }

    public void setEnemyTeam(List<TeamAnimal> enemyTeam) {
        this.enemyTeam = enemyTeam;
    }

    public void setIncomingDmg(int incomingDmg) {
        this.incomingDmg = incomingDmg;
        if (incomingDmg < 0) {
            incomingDmg = 0;
        }
    }

    public void setOutgoingDmg(int outgoingDmg) {
        this.outgoingDmg = outgoingDmg;
        if (outgoingDmg < 0) {
            outgoingDmg = 0;
        }
    }

    public void setLog(String log) {
        this.log = log;
    }

    public void addToMap(TeamAnimal copy, TeamAnimal origin) {
        copyToOrigin.put(copy, origin);
    }

    public Game getGame() {
        return this.game;
    }

    public List<TeamAnimal> getPlayerTeam() {
        return this.playerTeam;
    }

    public List<TeamAnimal> getEnemyTeam() {
        return this.enemyTeam;
    }

    public int getIncomingDmg() {
        return this.incomingDmg;
    }

    public int getOutgoingDmg() {
        return this.outgoingDmg;
    }

    public String getLog() {
        String log = this.log;
        this.log = "";
        return log;
    }

    public Map<TeamAnimal, TeamAnimal> getMap() {
        return this.copyToOrigin;
    }

    public void initMap(Fight fight) {
        if (fight.getPlayerTeam().getAllAnimals().isEmpty() || playerTeam.isEmpty()) {
            return;
        }
        for (int i = 0; i < playerTeam.size(); i++) {
            addToMap(playerTeam.get(i), fight.getPlayerTeam().getSlotByIndex(i));
        }
    }

}
