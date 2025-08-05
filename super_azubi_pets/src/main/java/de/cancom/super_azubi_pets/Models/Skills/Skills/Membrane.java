package de.cancom.super_azubi_pets.Models.Skills.Skills;

import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Models.Skills.FightState;
import de.cancom.super_azubi_pets.Models.Skills.Skill;
import de.cancom.super_azubi_pets.Models.Skills.Trigger;

public class Membrane implements Skill {

    public int shield;

    public Membrane(int level, int tier) {
        this.shield = Math.round((level / 4) * tier);
        if (shield <= 1) {
            shield = 2;
        }
    }

    @Override
    public String getName() {
        return "Membran";
    }

    @Override
    public String getDescription() {
        return "Erzeugt eine Membran die Schaden aufnehmen kann.";
    }

    @Override
    public Trigger getTrigger() {
        return Trigger.ON_ATTACK;
    }

    @Override
    public void apply(FightState state, String source) {

        if (shield <= 0) {
            return;
        }

        int dmg;
        TeamAnimal user;

        if (source.equals("player")) {
            user = state.getPlayerTeam().get(0);
            dmg = state.getIncomingDmg();
            source = "Spieler";
        } else {
            user = state.getEnemyTeam().get(0);
            dmg = state.getOutgoingDmg();
            source = "Gegner";
        }

        int preventedDmg = 0;

        while (shield > 0 && dmg > 0) {
            shield--;
            dmg--;
            preventedDmg++;
        }

        if (source.equals("Spieler")) {
            state.setIncomingDmg(dmg);
        } else {
            state.setOutgoingDmg(dmg);
        }

        state.setLog(state.getLog() + "[MEMBRAN] (" + user.getEmoji() + ", " + source + ") - Steckt " + preventedDmg
                + " Schaden weg.\n");

    }

}
