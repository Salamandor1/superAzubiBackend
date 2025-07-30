package de.cancom.super_azubi_pets.Models.Skills.Skills;

import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Models.Skills.FightState;
import de.cancom.super_azubi_pets.Models.Skills.Skill;
import de.cancom.super_azubi_pets.Models.Skills.Trigger;

public class Rage implements Skill {

    int hits = 0;
    private final double boost;

    public Rage(int level, int tier) {
        this.boost = 0.20 + (level / 100.0) + (tier / 10.0);
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
        return "Erhöht nach drei Treffern die Angriffskraft um " + (boost * 100) + "%.";
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
            target = state.getPlayerTeam().getFirst();
        } else {
            target = state.getEnemyTeam().getFirst();
        }
        if (target.getHealth() <= 0) {
            return;
        }
        target.setHealth((int) Math.round(target.getHealth() * (1.0 + boost)));
        if (source.equals("player")) {
            state.setLog(state.getLog() + "[RAGE] (" + target.getEmoji() + ", Spieler) - Angriff wurde um "
                    + (boost * 100) + "% erhöht.\n");
        } else {
            state.setLog(state.getLog() + "[RAGE] (" + target.getEmoji() + ", Gegner) - Angriff wurde um "
                    + (boost * 100) + "% erhöht.\n");
        }
    }
}
