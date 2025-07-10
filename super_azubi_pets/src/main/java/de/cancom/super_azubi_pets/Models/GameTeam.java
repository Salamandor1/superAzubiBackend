package de.cancom.super_azubi_pets.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "playerTeam")
public class GameTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // No use of list or similar, because the return value of a list from a database
    // is more complex to put into an object
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pos1_id")
    private TeamAnimal slot0;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pos2_id")
    private TeamAnimal slot1;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pos3_id")
    private TeamAnimal slot2;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pos4_id")
    private TeamAnimal slot3;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pos5_id")
    private TeamAnimal slot4;

    public GameTeam() {
    }

    public Long getID() {
        return id;
    }

    public TeamAnimal getSlot0() {
        return slot0;
    }

    public TeamAnimal getSlot1() {
        return slot1;
    }

    public TeamAnimal getSlot2() {
        return slot2;
    }

    public TeamAnimal getSlot3() {
        return slot3;
    }

    public TeamAnimal getSlot4() {
        return slot4;
    }

    public void setSlot0(TeamAnimal slot0) {
        this.slot0 = slot0;
    }

    public void setSlot1(TeamAnimal slot1) {
        this.slot1 = slot1;
    }

    public void setSlot2(TeamAnimal slot2) {
        this.slot2 = slot2;
    }

    public void setSlot3(TeamAnimal slot3) {
        this.slot3 = slot3;
    }

    public void setSlot4(TeamAnimal slot4) {
        this.slot4 = slot4;
    }
}
