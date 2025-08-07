package de.cancom.super_azubi_pets.Models.Skills.Skills;

import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Models.Skills.FightState;
import de.cancom.super_azubi_pets.Models.Skills.Skill;
import de.cancom.super_azubi_pets.Models.Skills.Trigger;

public class Rage implements Skill {

    private final int boost;

    public Rage(int level, int tier) {
        this.boost = (int) (1 + (Math.round(level / 10.0)) + (Math.round(tier / 2.0)));
    }

    @Override
    public String getName() {
        return "Rage";
    }

    @Override
    public Trigger getTrigger() {
        return Trigger.AFTER_ATTACK;
    }

    @Override
    public String getDescription() {
        return "Erhöht nach jedem Treffern die Angriffskraft um " + boost + ".";
    }

    @Override
    public void apply(FightState state, String source) {

        TeamAnimal target;
        if (source.equals("player")) {
            target = state.getPlayerTeam().get(0);
        } else {
            target = state.getEnemyTeam().get(0);
        }
        if (target.getHealth() <= 0) {
            return;
        }
        target.setAttack(target.getAttack() + boost);
        if (source.equals("player")) {
            state.setLog(state.getLog() + "[RAGE] (" + target.getEmoji() + ", Spieler) - Angriff wurde um "
                    + boost + " erhöht.\n");
        } else {
            state.setLog(state.getLog() + "[RAGE] (" + target.getEmoji() + ", Gegner) - Angriff wurde um "
                    + boost + " erhöht.\n");
        }
    }
}
