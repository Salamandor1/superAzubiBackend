package de.cancom.super_azubi_pets.Models.Skills.Skills;

import java.util.List;

import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Models.Skills.FightState;
import de.cancom.super_azubi_pets.Models.Skills.Skill;
import de.cancom.super_azubi_pets.Models.Skills.Trigger;

public class Sting implements Skill {

    private final int dmg;
    boolean wasPrinted = false;

    public Sting(int level, int tier) {
        this.dmg = (int) (Math.round((level * tier) / 20.0));
    }

    @Override
    public String getName() {
        return "Stich";
    }

    @Override
    public String getDescription() {
        return "Damages all enemy animals every round.";
    }

    @Override
    public Trigger getTrigger() {
        if (wasPrinted) {
            return Trigger.ON_ROUND_END;
        } else {
            return Trigger.ON_ROUND_START;
        }
    }

    @Override
    public void apply(FightState state, String source) {

        if (Trigger.ON_ROUND_END == getTrigger()) {
            setWasPrinted(false);
            return;
        }

        List<TeamAnimal> users;
        List<TeamAnimal> targets;
        String user;
        String animalUser = "";

        if (source.equals("player")) {
            targets = state.getEnemyTeam();
            user = "Spieler";
            users = state.getPlayerTeam();
        } else {
            targets = state.getPlayerTeam();
            user = "Gegner";
            users = state.getEnemyTeam();
        }

        for (TeamAnimal animal : users) {
            if (animal.getSkill() instanceof Sting && !this.wasPrinted) {
                animalUser += animal.getEmoji();
                setWasPrinted(true);
            }
        }

        for (TeamAnimal target : targets) {
            target.setHealth(target.getHealth() - dmg);
        }

        state.setLog(state.getLog() + "[Stich] " + animalUser + " (" + user + ") fügt allen Gegnern " + dmg
                + " Schaden zu.\n");

    }

    public void setWasPrinted(boolean wasPrinted) {
        this.wasPrinted = wasPrinted;
    }

}
