package de.cancom.super_azubi_pets.Models.Skills.Skills;

import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Models.Skills.FightState;
import de.cancom.super_azubi_pets.Models.Skills.Skill;
import de.cancom.super_azubi_pets.Models.Skills.Trigger;

public class Thorns implements Skill {

    private final double factor;

    public Thorns(int level, int tier) {
        this.factor = 0.1 + (level / 50.0) + (tier / 20);
    }

    @Override
    public String getName() {
        return "Dornen";
    }

    @Override
    public String getDescription() {
        return "Reflektiert Schaden.";
    }

    @Override
    public Trigger getTrigger() {
        return Trigger.ON_ATTACK;
    }

    @Override
    public void apply(FightState state, String source, TeamAnimal user) {
        TeamAnimal target;
        String from;
        String to;
        int dmg;
        String newSource;
        if (source.equals("player")) {
            target = state.getEnemyTeam().get(0);
            dmg = state.getIncomingDmg();
            from = "Spieler";
            to = "Gegner";
            newSource = "enemy";
        } else {
            user = state.getEnemyTeam().get(0);
            target = state.getPlayerTeam().get(0);
            dmg = state.getOutgoingDmg();
            from = "Gegner";
            to = "Spieler";
            newSource = "player";
        }
        if (target.getHealth() <= 0) {
            return;
        }
        dmg = (int) (Math.round(dmg * factor));
        if (dmg < 1) {
            dmg = 1;
        }
        target.setHealth(target.getHealth() - dmg);
        state.setLog(state.getLog() + "[DORNEN] " + user.getEmoji() + "(" + from + ") reflektiert " + dmg
                + " Schaden gegen " + target.getEmoji() + "(" + to + "). ");

        if (target.getHealth() <= 0) {
            state.setLog(state.getLog() + target.getEmoji() + "wurde besiegt.\n");
            Skill skill = target.getSkill();
            if (skill.getTrigger() == Trigger.ON_OWN_DEATH) {
                skill.apply(state, newSource, target);
            }
        } else {
            state.setLog(state.getLog() + "\n");
        }
    }

}
