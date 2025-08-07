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

    public int getCharges() {
        return charges;
    }

    public void reduceCharges() {
        this.charges--;
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

        List<TeamAnimal> targets;
        String from;
        String to;
        int dmg;
        String newSource;

        if (source.equals("player")) {
            if (state.getIncomingDmg() <= 0) {
                return;
            }
            targets = state.getEnemyTeam();
            from = "Spieler";
            to = "Gegner";
            newSource = "enemy";
            state.setOutgoingDmg((int) (Math.round(state.getIncomingDmg() / 2.0)));
        } else {
            if (state.getOutgoingDmg() <= 0) {
                return;
            }
            targets = state.getPlayerTeam();
            from = "Gegner";
            to = "Spieler";
            newSource = "player";
            state.setIncomingDmg((int) (Math.round(state.getOutgoingDmg() / 2.0)));
        }

        if (targets.size() < 2) {
            return;
        } else {
            charges--;
        }

        if (charges < 1) {
            user.setSkill(new None(0, 0));
        }

        int index = (int) (Math.random() * (targets.size() - 1)) + 1;
        TeamAnimal target = targets.get(index);

        Skill skill = target.getSkill();
        if (skill.getTrigger() == Trigger.BEFORE_ATTACK || skill.getTrigger() == Trigger.ON_ATTACK) {
            if (skill instanceof Charm) {

            } else {
                skill.apply(state, newSource, target);
            }
        }

        if (source.equals("player")) {
            dmg = state.getOutgoingDmg();
        } else {
            dmg = state.getIncomingDmg();
        }

        target.setHealth(target.getHealth() - dmg);

        state.setLog(state.getLog() + "[CHARM](" + user.getEmoji() + ", " + from + ") - leitet " + dmg + " Schaden an "
                + target.getEmoji() + " (" + to + ") weiter.");

        if (skill.getTrigger() == Trigger.ON_DAMAGE || skill.getTrigger() == Trigger.AFTER_ATTACK) {
            state.setLog(state.getLog() + "\n");
            skill.apply(state, newSource, target);
        }

        if (source.equals("player")) {
            Skill skill2 = state.getEnemyTeam().get(0).getSkill();
            state.setOutgoingDmg(state.getOutgoingDmg() * 2);
            state.setLog(state.getLog() + "\n");
            if (skill2 instanceof Charm) {
                skill2.apply(state, "enemy", state.getEnemyTeam().get(0));
            }
        } else {
            Skill skill2 = state.getPlayerTeam().get(0).getSkill();
            state.setIncomingDmg(state.getIncomingDmg() * 2);
            state.setLog(state.getLog() + "\n");
            if (skill2 instanceof Charm) {
                skill2.apply(state, "player", state.getPlayerTeam().get(0));
            }
        }

        state.setIncomingDmg(0);
        state.setOutgoingDmg(0);

        if (target.getHealth() <= 0) {
            state.setLog(state.getLog() + target.getEmoji() + "(" + to + ") wurde besiegt.\n");
            if (skill.getTrigger() == Trigger.ON_OWN_DEATH) {
                skill.apply(state, newSource, target);
            }
        } else {
            state.setLog(state.getLog() + "\n");
        }

    }

}
