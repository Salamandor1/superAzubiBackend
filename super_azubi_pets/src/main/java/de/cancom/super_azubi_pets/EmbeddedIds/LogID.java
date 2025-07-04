package de.cancom.super_azubi_pets.EmbeddedIds;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class LogID implements Serializable {

    @Column(name = "player_team_id")
    private Long playerTeamID;

    @Column(name = "npc_team_id")
    private Long npcTeamID;

    public LogID() {
    }

    public LogID(Long playerTeamID, Long npcTeamID) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof LogID))
            return false;
        LogID that = (LogID) o;
        return Objects.equals(playerTeamID, that.playerTeamID) &&
                Objects.equals(npcTeamID, that.npcTeamID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerTeamID, npcTeamID);
    }

}
