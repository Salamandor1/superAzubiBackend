package de.cancom.super_azubi_pets.Models;

public class Fight {

    private Team playerTeam;
    private Team npcTeam;
    private Log log;

    public Fight() {
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

    public void setLog(Log log) {
        this.log = log;
    }

    public Team getPlayerTeam() {
        return playerTeam;
    }

    public Team getNpcTeam() {
        return npcTeam;
    }

    public Log getLog() {
        return log;
    }

}
