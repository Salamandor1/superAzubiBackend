package de.cancom.super_azubi_pets.Models;

public class Fight {

    private GameTeam playerTeam;
    private GameTeam npcTeam;
    private Log log;

    public Fight() {
    }

    public Fight(GameTeam playerTeam, GameTeam npcTeam) {
        this.playerTeam = playerTeam;
        this.npcTeam = npcTeam;
    }

    public void setPlayerTeam(GameTeam playerTeam) {
        this.playerTeam = playerTeam;
    }

    public void setNpcTeam(GameTeam npcTeam) {
        this.npcTeam = npcTeam;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public GameTeam getPlayerTeam() {
        return playerTeam;
    }

    public GameTeam getNpcTeam() {
        return npcTeam;
    }

    public Log getLog() {
        return log;
    }

}
