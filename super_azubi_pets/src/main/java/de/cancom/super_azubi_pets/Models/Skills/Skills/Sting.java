package de.cancom.super_azubi_pets.Models.Skills.Skills;

import java.util.List;

import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Models.Skills.FightState;
import de.cancom.super_azubi_pets.Models.Skills.Skill;
import de.cancom.super_azubi_pets.Models.Skills.Trigger;

public class Sting implements Skill {

    private final int dmg;

    public Sting(int level, int tier) {
        int dmg = (int) (Math.round((level * tier) / 5.0));
        if (dmg <= 0) {
            this.dmg = 1;
        } else {
            this.dmg = dmg;
        }
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
        return Trigger.ON_GAME_START;
    }

    @Override
    public void apply(FightState state, String source) {

        List<TeamAnimal> users;
        List<TeamAnimal> targets;
        String user;
        String target;
        String animalUser = "";
        String animalTarget = "";

        if (source.equals("player")) {
            target = "Gegner";
            targets = state.getEnemyTeam();
            user = "Spieler";
            users = state.getPlayerTeam();
        } else {
            target = "Spieler";
            targets = state.getPlayerTeam();
            user = "Gegner";
            users = state.getEnemyTeam();
        }

        for (TeamAnimal animal : users) {
            if (animal.getSkill() instanceof Sting) {
                animalUser += animal.getEmoji();
                animal.setSkill(new None(0, 0));
                break;
            }
        }

        boolean didOneDie = false;
        for (TeamAnimal animal : targets) {
            animal.setHealth(animal.getHealth() - dmg);
            if (animal.getHealth() <= 0) {
                animalTarget += animal.getEmoji();
                didOneDie = true;
            }
        }

        if (didOneDie) {
            animalTarget += "(" + target + ") wurde/n besiegt.";
        }

        state.setLog(state.getLog() + "[Stich] " + animalUser + " (" + user + ") fügt allen Gegnern " + dmg
                + " Schaden zu. " + animalTarget + "\n");

    }

}
