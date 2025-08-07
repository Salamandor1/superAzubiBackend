package de.cancom.super_azubi_pets.Models;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long team_id;

    // No use of list or similar, because the return value of a list from a database
    // is more complex to put into an object
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pos1_id", referencedColumnName = "team_animal_id")
    private TeamAnimal slot0;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pos2_id", referencedColumnName = "team_animal_id")
    private TeamAnimal slot1;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pos3_id", referencedColumnName = "team_animal_id")
    private TeamAnimal slot2;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pos4_id", referencedColumnName = "team_animal_id")
    private TeamAnimal slot3;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pos5_id", referencedColumnName = "team_animal_id")
    private TeamAnimal slot4;

    public Team() {
    }

    public Team(Team team) {
        for (int i = 0; i < 5; i++) {
            if (team.getSlotByIndex(i) == null) {
                this.setSlotByIndex(null, i);
            } else {
                this.setSlotByIndex(new TeamAnimal(team.getSlotByIndex(i)), i);
            }
        }
    }

    public Long getID() {
        return team_id;
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

    // getter for flexibility
    public TeamAnimal getSlotByIndex(int i) {
        switch (i) {
            case 0:
                return getSlot0();
            case 1:
                return getSlot1();
            case 2:
                return getSlot2();
            case 3:
                return getSlot3();
            case 4:
                return getSlot4();
            default:
                throw new IllegalArgumentException("Index out of bounds: possible index 0 - 4");
        }
    }

    public List<TeamAnimal> getAllAnimals() {
        return Arrays.asList(slot0, slot1, slot2, slot3, slot4);
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

    // setter for flexibility
    public void setSlotByIndex(TeamAnimal teamAnimal, int i) {
        switch (i) {
            case 0:
                setSlot0(teamAnimal);
                break;
            case 1:
                setSlot1(teamAnimal);
                break;
            case 2:
                setSlot2(teamAnimal);
                break;
            case 3:
                setSlot3(teamAnimal);
                break;
            case 4:
                setSlot4(teamAnimal);
                break;
            default:
                throw new IllegalArgumentException("Index out of bounds. Possible index: 0-4.");
        }
    }

    public void setAllAnimals(List<TeamAnimal> animals) {
        if (animals.size() > 5) {
            throw new IllegalArgumentException("Index out of bounds. Max count of animals: 5");
        }
        setSlot0(animals.get(0));
        setSlot1(animals.get(1));
        setSlot2(animals.get(2));
        setSlot3(animals.get(3));
        setSlot4(animals.get(4));
    }
}
