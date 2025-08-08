package de.cancom.super_azubi_pets.Models.Skills.Skills;

import java.util.List;

import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Models.Skills.FightState;
import de.cancom.super_azubi_pets.Models.Skills.Skill;
import de.cancom.super_azubi_pets.Models.Skills.Trigger;

public class Trample implements Skill {

    private final double factor;

    public Trample(int level, int tier) {
        this.factor = 0.2 + (tier / 10.0) + (level / 100.0);
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
    public void apply(FightState state, String source, TeamAnimal user) {

        String from;
        String to;
        TeamAnimal target;
        int dmg;

        if (source.equals("player")) {
            if (user != state.getPlayerTeam().get(0)) {
                return;
            }
            source = "enemy";
            from = "Spieler";
            to = "Gegner";
            target = getNext(state.getEnemyTeam());
            dmg = state.getOutgoingDmg();
        } else {
            if (user != state.getEnemyTeam().get(0)) {
                return;
            }
            source = "player";
            from = "Gegner";
            to = "Spieler";
            target = getNext(state.getPlayerTeam());
            dmg = state.getIncomingDmg();
        }

        if (dmg == 0) {
            return;
        }

        if (target == null) {
            return;
        }

        dmg = (int) (Math.round(dmg * factor));

        if (dmg < 1) {
            dmg = 1;
        }

        target.setHealth(target.getHealth() - dmg);

        state.setLog(state.getLog() + "[TRAMPEL] " + user.getEmoji() + "(" + from + ") verursacht ebenfalls " + dmg
                + " Schaden bei " + target.getEmoji() + "(" + to + "). ");

        Skill skill = target.getSkill();

        if (target.getHealth() <= 0) {
            state.setLog(state.getLog() + target.getEmoji() + " wurde besiegt.\n");
            if (skill.getTrigger() == Trigger.ON_OWN_DEATH) {
                skill.apply(state, source, target);
            }
        } else {
            state.setLog(state.getLog() + "\n");
            if (skill.getTrigger() == Trigger.ON_DAMAGE) {
                skill.apply(state, source, target);
            }
        }

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
