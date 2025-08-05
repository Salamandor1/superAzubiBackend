package de.cancom.super_azubi_pets.Models.Skills.Skills;

import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Models.Skills.FightState;
import de.cancom.super_azubi_pets.Models.Skills.Skill;
import de.cancom.super_azubi_pets.Models.Skills.Trigger;

public class Supply implements Skill {

    private final int bonus;

    public Supply(int level, int tier) {
        this.bonus = 1 + (level / 2) + (tier);
    }

    @Override
    public String getName() {
        return "Vorrat";
    }

    @Override
    public String getDescription() {
        return "Wenn das Tier nach einem Angriff noch lebt, heilt es sich.";
    }

    @Override
    public Trigger getTrigger() {
        return Trigger.AFTER_ATTACK;
    }

    @Override
    public void apply(FightState state, String source) {

        TeamAnimal user;

        if (source.equals("player")) {
            user = state.getPlayerTeam().get(0);
            source = "Spieler";
        } else {
            user = state.getEnemyTeam().get(0);
            source = "Gegner";
        }

        if (user.getHealth() <= 0) {
            return;
        } else {
            user.setSkill(new None(0, 0));
        }

        user.setHealth(user.getHealth() + bonus);

        state.setLog(state.getLog() + "[VORRAT](" + user.getEmoji() + ", " + source + ") - Heilt sich um ❤️" + bonus
                + ".\n");

    }

}
