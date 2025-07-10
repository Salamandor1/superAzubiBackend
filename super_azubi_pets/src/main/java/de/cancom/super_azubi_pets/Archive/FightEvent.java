package de.cancom.super_azubi_pets.Archive;

import de.cancom.super_azubi_pets.Models.Fight;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "fight_event")
public class FightEvent {

    @EmbeddedId
    private FightEventId id;

    @ManyToOne
    @MapsId("fightId")
    @JoinColumn(name = "fight_id")
    private Fight fight;

    private String event;

    public FightEventId getId() {
        return id;
    }

    public void setId(FightEventId id) {
        this.id = id;
    }

    public Fight getFight() {
        return fight;
    }

    public void setFight(Fight fight) {
        this.fight = fight;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
