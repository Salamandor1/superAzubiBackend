package de.cancom.super_azubi_pets.Models.Skills.Skills;

import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Models.Skills.FightState;
import de.cancom.super_azubi_pets.Models.Skills.Skill;
import de.cancom.super_azubi_pets.Models.Skills.Trigger;

public class Annoying implements Skill {

    private int charges;

    public Annoying(int level, int tier) {
        switch (tier) {
            case 4, 5, 6:
                charges = 2;
                break;
            case 7:
                charges = 3;
                break;
            default:
                charges = 1;
        }
    }

    @Override
    public String getName() {
        return "Nervtöter";
    }

    @Override
    public String getDescription() {
        return "Deaktiviert die Fähigkeit des Gegners.";
    }

    @Override
    public Trigger getTrigger() {
        return Trigger.ON_ROUND_START;
    }

    @Override
    public void apply(FightState state, String source) {

        if (charges <= 0) {
            return;
        } else {
            charges--;
        }

        TeamAnimal user;
        TeamAnimal target;
        String who;

        if (source.equals("player")) {
            user = state.getPlayerTeam().get(0);
            target = state.getEnemyTeam().get(0);
            source = "Spieler";
            who = "Gegner";
        } else {
            user = state.getEnemyTeam().get(0);
            target = state.getPlayerTeam().get(0);
            source = "Gegner";
            who = "Spieler";
        }

        target.setSkill(new None(0, 0));

        state.setLog(state.getLog() + "[NERVTÖTER] (" + user.getEmoji() + ", " + source
                + ") - verhindert den Einsatz der Fähigkeit von " + target.getEmoji() + "(" + who + ").");

    }

}
