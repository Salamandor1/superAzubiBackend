package de.cancom.super_azubi_pets.DTOs;

public class LogQueryDTO {

    private Long playerTeamID;
    private Long npcTeamID;

    public LogQueryDTO() {
    }

    public LogQueryDTO(Long playerTeamID, Long npcTeamID) {
        this.playerTeamID = playerTeamID;
        this.npcTeamID = npcTeamID;
    }

    public Long getPlayerTeamID() {
        return playerTeamID;
    }

    public void setPlayerTeamID(Long playerTeamID) {
        this.playerTeamID = playerTeamID;
    }

    public Long getNpcTeamID() {
        return npcTeamID;
    }

    public void setNpcTeamID(Long npcTeamID) {
        this.npcTeamID = npcTeamID;
    }

}
