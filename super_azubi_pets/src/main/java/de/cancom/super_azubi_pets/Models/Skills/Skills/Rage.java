package de.cancom.super_azubi_pets.Models.Skills.Skills;

import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Models.Skills.FightState;
import de.cancom.super_azubi_pets.Models.Skills.Skill;
import de.cancom.super_azubi_pets.Models.Skills.Trigger;

public class Rage implements Skill {

    int hits = 0;
    private final double boost;

    public Rage(int level, int tier) {
        this.boost = 0.10 + (level / 100.0) + (tier / 10.0);
        if (level >= 10 || tier >= 5) {
            hits++;
        }
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
        return "Erhöht nach drei Treffern die Angriffskraft um " + Math.round(boost * 100) + "%.";
    }

    @Override
    public void apply(FightState state, String source) {
        hits++;
        if (hits < 3) {
            return;
        }
        hits = 0;
        TeamAnimal target;
        if (source.equals("player")) {
            target = state.getPlayerTeam().get(0);
        } else {
            target = state.getEnemyTeam().get(0);
        }
        if (target.getHealth() <= 0) {
            return;
        }
        target.setAttack((int) Math.round(target.getAttack() * (1.0 + boost)));
        if (source.equals("player")) {
            state.setLog(state.getLog() + "[RAGE] (" + target.getEmoji() + ", Spieler) - Angriff wurde um "
                    + Math.round(target.getAttack() * (1.0 + boost)) + " erhöht.\n");
        } else {
            state.setLog(state.getLog() + "[RAGE] (" + target.getEmoji() + ", Gegner) - Angriff wurde um "
                    + Math.round(target.getAttack() * (1.0 + boost)) + " erhöht.\n");
        }
    }
}
