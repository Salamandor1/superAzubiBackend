package de.cancom.super_azubi_pets.Models.Skills.Skills;

import java.util.List;

import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Models.Skills.FightState;
import de.cancom.super_azubi_pets.Models.Skills.Skill;
import de.cancom.super_azubi_pets.Models.Skills.Trigger;

public class Charm implements Skill {

    private int charges;

    public Charm(int level, int tier) {
        switch (tier) {
            case 7:
                charges = 3;
                break;
            case 6, 5:
                charges = 2;
                break;
            default:
                charges = 1;
        }
    }

    @Override
    public String getName() {
        return "Charm";
    }

    @Override
    public String getDescription() {
        return "Leitet Angriff mit halber Stärke auf zufälligen Gegner weiter.";
    }

    @Override
    public Trigger getTrigger() {
        return Trigger.ON_ATTACK;
    }

    @Override
    public void apply(FightState state, String source, TeamAnimal user) {

        if (charges <= 0) {
            return;
        } else {
            charges--;
        }

        List<TeamAnimal> targets;
        String from;
        String to;
        int dmg;
        String newSource;

        if (source.equals("player")) {
            targets = state.getEnemyTeam();
            from = "Spieler";
            to = "Gegner";
            newSource = "enemy";
            state.setIncomingDmg(0);
            state.setOutgoingDmg((int) (Math.round(targets.get(0).getAttack() / 2.0)));
        } else {
            targets = state.getPlayerTeam();
            from = "Gegner";
            to = "Spieler";
            newSource = "player";
            state.setIncomingDmg((int) (Math.round(targets.get(0).getAttack() / 2.0)));
            state.setOutgoingDmg(0);
        }

        if (targets.size() < 2) {
            return;
        }

        int index = (int) (Math.random() * (targets.size() - 1)) + 1;
        TeamAnimal target = targets.get(index);

        Skill skill = target.getSkill();
        if (skill.getTrigger() == Trigger.BEFORE_ATTACK || skill.getTrigger() == Trigger.ON_ATTACK) {
            skill.apply(state, newSource, target);
        }

        if (source.equals("player")) {
            dmg = state.getIncomingDmg();
        } else {
            dmg = state.getOutgoingDmg();
        }

        state.setIncomingDmg(0);
        state.setOutgoingDmg(0);

        target.setHealth(target.getHealth() - dmg);

        state.setLog(state.getLog() + "[CHARM](" + user.getEmoji() + ", " + from + ") - leitet " + dmg + " Schaden an "
                + target.getEmoji() + " (" + to + ") weiter.");

        if (skill.getTrigger() == Trigger.ON_DAMAGE) {
            skill.apply(state, newSource, target);
        }

        if (target.getHealth() <= 0) {
            state.setLog(state.getLog() + target.getEmoji() + " wurde besiegt.");
            if (skill.getTrigger() == Trigger.ON_OWN_DEATH) {
                skill.apply(state, newSource, target);
            }
        }

        state.setLog(state.getLog() + "\n");

    }

}
