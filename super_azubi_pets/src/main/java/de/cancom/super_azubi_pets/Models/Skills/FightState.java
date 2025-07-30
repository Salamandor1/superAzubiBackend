package de.cancom.super_azubi_pets.Models.Skills;

import java.util.List;

import de.cancom.super_azubi_pets.Models.TeamAnimal;

public class FightState {
    private List<TeamAnimal> playerTeam;
    private List<TeamAnimal> enemyTeam;
    private int incomingDmg;
    private int outgoingDmg;
    private String log;

    public FightState(List<TeamAnimal> playerTeam, List<TeamAnimal> enemyTeam) {
        setPlayerTeam(playerTeam);
        setEnemyTeam(enemyTeam);
        setIncomingDmg(0);
        setOutgoingDmg(0);
        setLog("");
    }

    public void setPlayerTeam(List<TeamAnimal> playerTeam) {
        this.playerTeam = playerTeam;
    }

    public void setEnemyTeam(List<TeamAnimal> enemyTeam) {
        this.enemyTeam = enemyTeam;
    }

    public void setIncomingDmg(int incomingDmg) {
        this.incomingDmg = incomingDmg;
    }

    public void setOutgoingDmg(int outgoingDmg) {
        this.outgoingDmg = outgoingDmg;
    }

    public void setLog(String log) {
        this.log = log;
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

}
