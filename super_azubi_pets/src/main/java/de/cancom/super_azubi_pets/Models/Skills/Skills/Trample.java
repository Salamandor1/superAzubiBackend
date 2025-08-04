package de.cancom.super_azubi_pets.Models.Skills.Skills;

import java.util.List;

import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Models.Skills.FightState;
import de.cancom.super_azubi_pets.Models.Skills.Skill;
import de.cancom.super_azubi_pets.Models.Skills.Trigger;

public class Trample implements Skill {

    private final double factor;

    public Trample(int level, int tier) {
        this.factor = 0.1 + Math.round((level * tier) / 240.0);
    }

    @Override
    public String getName() {
        return "Trampel";
    }

    @Override
    public String getDescription() {
        return "Fügt nächstem Gegner ebenfalls Schaden zu.";
    }

    @Override
    public Trigger getTrigger() {
        return Trigger.ON_ATTACK;
    }

    @Override
    public void apply(FightState state, String source) {

        String targetPlayer;
        TeamAnimal user;
        TeamAnimal target;
        int dmg;

        if (source.equals("player")) {
            source = "Spieler";
            targetPlayer = "Gegner";
            user = state.getPlayerTeam().get(0);
            target = getNext(state.getEnemyTeam());
            dmg = state.getOutgoingDmg();
        } else {
            source = "Gegner";
            targetPlayer = "Spieler";
            user = state.getEnemyTeam().get(0);
            target = getNext(state.getPlayerTeam());
            dmg = state.getIncomingDmg();
        }

        if (target == null) {
            System.out.println("No Valid Animal Found.");
            return;
        }

        dmg = (int) (Math.round(dmg * factor));

        if (dmg <= 0) {
            dmg = 1;
        }

        target.setHealth(target.getHealth() - dmg);

        String append = "";
        if (target.getHealth() <= 0) {
            append = target.getEmoji() + " stirbt.";
        }

        state.setLog(state.getLog() + "[TRAMPEL] " + user.getEmoji() + "(" + source + ") verursacht ebenfalls " + dmg
                + " Schaden bei " + target.getEmoji() + "(" + targetPlayer + "). " + append + "\n");

    }

    private TeamAnimal getNext(List<TeamAnimal> team) {
        boolean skipFirst = true;
        for (TeamAnimal animal : team) {
            if (skipFirst) {
                skipFirst = false;
                continue;
            }
            if (animal != null) {
                return animal;
            }
        }
        return null;
    }

}
