package de.cancom.super_azubi_pets.Models.Skills.Skills;

import de.cancom.super_azubi_pets.Models.Skills.FightState;
import de.cancom.super_azubi_pets.Models.Skills.Skill;
import de.cancom.super_azubi_pets.Models.Skills.Trigger;

public class Shield implements Skill {

    private int charges;

    public Shield(int level, int tier) {
        switch (tier) {
            case 1, 2, 3, 4:
                setCharges(1);
                break;
            case 5, 6:
                setCharges(2);
                break;
            default:
                setCharges(1);
        }
        if (level >= 15) {
            this.charges++;
        }
    }

    @Override
    public String getName() {
        return "Shield";
    }

    @Override
    public Trigger getTrigger() {
        return Trigger.BEFORE_ATTACK;
    }

    @Override
    public String getDescription() {
        return "Blockt Schaden " + charges + " mal komplett.";
    }

    @Override
    public void apply(FightState state, String source) {
        if (this.charges > 0) {
            this.charges--;
            if (source.equals("player")) {
                state.setIncomingDmg(0);
                state.setLog(state.getLog() + "[SCHILD] (" + state.getPlayerTeam().get(0).getEmoji()
                        + ", Spieler" + ") - Schaden wird verhindert.\n");
            } else {
                state.setOutgoingDmg(0);
                state.setLog(state.getLog() + "[SCHILD] (" + state.getEnemyTeam().get(0).getEmoji()
                        + ", Gegner" + ") - Schaden wird verhindert.\n");
            }
        }
    }

    private void setCharges(int charges) {
        this.charges = charges;
    }

}
