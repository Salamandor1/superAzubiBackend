package de.cancom.super_azubi_pets.EmbeddedIds;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class FightEventId implements Serializable {
    private Long fightId;
    private Long index;

    public FightEventId() {
    }

    public FightEventId(Long fightId, Long index) {
        this.fightId = fightId;
        this.index = index;
    }

    public Long getFighttId() {
        return this.fightId;
    }

    public Long getIndex() {
        return this.index;
    }

    public void setFightId(Long fightId) {
        this.fightId = fightId;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

}
