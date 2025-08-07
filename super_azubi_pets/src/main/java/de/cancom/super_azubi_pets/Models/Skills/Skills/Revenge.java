package de.cancom.super_azubi_pets.Models.Skills.Skills;

import java.util.List;

import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Models.Skills.FightState;
import de.cancom.super_azubi_pets.Models.Skills.Skill;
import de.cancom.super_azubi_pets.Models.Skills.Trigger;

public class Revenge implements Skill {

    private int damage;

    public Revenge(int level, int tier) {
        this.damage = 5 + ((level / 4) * (tier / 2));
    }

    @Override
    public String getName() {
        return "Rache";
    }

    @Override
    public String getDescription() {
        return "Fügt einem zufälligen Gegner " + damage + " Schaden zu.";
    }

    @Override
    public Trigger getTrigger() {
        return Trigger.ON_DAMAGE;
    }

    @Override
    public void apply(FightState state, String source, TeamAnimal user) {

        if (user.getHealth() <= 0) {
            return;
        }

        List<TeamAnimal> targets;
        TeamAnimal target;
        String from;
        String to;
        String newSource;

        if (source.equals("player")) {
            from = "Spieler";
            to = "Gegner";
            newSource = "enemy";
            targets = state.getEnemyTeam();
            state.setIncomingDmg(0);
            state.setOutgoingDmg(damage);
        } else {
            from = "Gegner";
            to = "Spieler";
            newSource = "player";
            targets = state.getPlayerTeam();
            state.setIncomingDmg(damage);
            state.setOutgoingDmg(0);
        }

        if (targets.size() < 2) {
            return;
        }

        int index = (int) (Math.random() * (targets.size() - 1)) + 1;
        target = targets.get(index);

        String log = state.getLog();

        Skill skill = target.getSkill();
        if (skill.getTrigger() == Trigger.BEFORE_ATTACK || skill.getTrigger() == Trigger.ON_ATTACK) {
            skill.apply(state, newSource, target);
        }

        if (source.equals("player")) {
            damage = state.getOutgoingDmg();
        } else {
            damage = state.getIncomingDmg();
        }

        target.setHealth(target.getHealth() - damage);

        state.setLog(log + "[RACHE](" + user.getEmoji() + ", " + from + ") - rächt sich und fügt "
                + target.getEmoji() + "(" + to + ") " + damage + " Schaden zu. " + state.getLog());

        if (skill.getTrigger() == Trigger.ON_DAMAGE) {
            skill.apply(state, newSource, target);
        }

        if (target.getHealth() <= 0) {
            state.setLog(state.getLog() + target.getEmoji() + "(" + to + ") wurde besiegt.");
            if (skill.getTrigger() == Trigger.ON_OWN_DEATH) {
                skill.apply(state, newSource, target);
            }
        }

        state.setLog(state.getLog() + "\n");
    }
}
