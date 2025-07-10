package de.cancom.super_azubi_pets.Models;

import de.cancom.super_azubi_pets.Archive.Team;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "fight")
public class Fight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fight_id")
    private Long fightID;

    @ManyToOne
    @JoinColumn(name = "team_1_id", referencedColumnName = "teamID")
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "team_2_id", referencedColumnName = "teamID")
    private Team team2;

    public Fight() {
    }

    public Fight(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    public Long getFightID() {
        return this.fightID;
    }

    public Team getTeam1() {
        return this.team1;
    }

    public Team getTeam2() {
        return this.team2;
    }

    // maybe not needed, but could be relevant for testing
    public void setFightID(Long fightID) {
        this.fightID = fightID;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

}
