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
    public void apply(FightState state, String source) {

        if (charges <= 0) {
            return;
        } else {
            charges--;
        }

        TeamAnimal user;
        List<TeamAnimal> targets;
        String from;
        String to;
        int dmg;

        if (source.equals("player")) {
            user = state.getPlayerTeam().get(0);
            targets = state.getEnemyTeam();
            from = "Spieler";
            to = "Gegner";
        } else {
            user = state.getEnemyTeam().get(0);
            targets = state.getPlayerTeam();
            from = "Gegner";
            to = "Spieler";
        }
        state.setIncomingDmg(0);
        state.setOutgoingDmg(0);

        if (targets.size() < 2) {
            return;
        }

        int index = (int) (Math.random() * (targets.size() - 1)) + 1;
        TeamAnimal target = targets.get(index);
        dmg = (int) (Math.round(targets.get(0).getAttack() / 2.0));

        target.setHealth(target.getHealth() - dmg);

        state.setLog(state.getLog() + "[CHARM](" + user.getEmoji() + ", " + from + ") - leitet " + dmg + " Schaden an "
                + target.getEmoji() + " (" + to + ") weiter.");

        if (target.getHealth() <= 0) {
            state.setLog(state.getLog() + target.getEmoji() + " wurde besiegt.");
        }

        state.setLog(state.getLog() + "\n");

    }

}
