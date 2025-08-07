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
        return Trigger.ON_DAMAGE;
    }

    @Override
    public String getDescription() {
        return "Erhöht nach jedem Treffern die Angriffskraft um " + boost + ".";
    }

    @Override
    public void apply(FightState state, String source, TeamAnimal user) {

        int dmg;
        if (source.equals("player")) {
            dmg = state.getIncomingDmg();
        } else {
            dmg = state.getOutgoingDmg();
        }
        if (user.getHealth() <= 0) {
            return;
        }
        if (dmg < 1) {
            return;
        }
        user.setAttack(user.getAttack() + boost);
        if (source.equals("player")) {
            state.setLog(state.getLog() + "[RAGE] (" + user.getEmoji() + ", Spieler) - Angriff wurde um "
                    + boost + " erhöht.\n");
        } else {
            state.setLog(state.getLog() + "[RAGE] (" + user.getEmoji() + ", Gegner) - Angriff wurde um "
                    + boost + " erhöht.\n");
        }
    }
}
