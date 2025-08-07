package de.cancom.super_azubi_pets.Models.Skills.Skills;

import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Models.Skills.FightState;
import de.cancom.super_azubi_pets.Models.Skills.Skill;
import de.cancom.super_azubi_pets.Models.Skills.Trigger;

public class Block implements Skill {

    private final int level;
    private final int tier;

    public Block(int level, int tier) {
        this.level = level;
        this.tier = tier;
    }

    @Override
    public String getName() {
        return "Block";
    }

    @Override
    public String getDescription() {
        return "Bockt " + (getBlockPercentage() * 100) + "% des Schadens.";
    }

    @Override
    public Trigger getTrigger() {
        return Trigger.ON_ATTACK;
    }

    @Override
    public void apply(FightState state, String source, TeamAnimal user) {
        int dmg;

        if (isSourcePlayer(source)) {
            dmg = state.getIncomingDmg();
        } else {
            dmg = state.getOutgoingDmg();
        }

        if (dmg < 1) {
            return;
        }

        int blockedDmg = (int) (Math.round(dmg * (getBlockPercentage())));

        if (blockedDmg == 0) {
            blockedDmg = 1;
        }
        if (dmg == blockedDmg) {
            blockedDmg--;
        }
        if (isSourcePlayer(source)) {
            state.setIncomingDmg(dmg - blockedDmg);
        } else {
            state.setOutgoingDmg(dmg - blockedDmg);
        }
        state.setLog(state.getLog() + "[BLOCK] (" + user.getEmoji()
                + ", Spieler) - Schaden wurde um " + blockedDmg + " reduziert.\n");
    }

    public boolean isSourcePlayer(String source) {
        return source.equals("player");
    }

    public double getBlockPercentage() {
        return 0.05 + (tier / 30.0) + (level / 200.0);
    }

}
