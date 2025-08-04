package de.cancom.super_azubi_pets.Models.Skills.Skills;

import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Models.Skills.FightState;
import de.cancom.super_azubi_pets.Models.Skills.Skill;
import de.cancom.super_azubi_pets.Models.Skills.Trigger;

public class Hide implements Skill {

    boolean isActive = false;
    boolean didUse = false;

    public Hide(int level, int tier) {

    }

    @Override
    public String getName() {
        return "Verstecken";
    }

    @Override
    public String getDescription() {
        return "Versteckt sich nach erstem Treffer.";
    }

    @Override
    public Trigger getTrigger() {
        if (isActive) {
            return Trigger.BEFORE_ATTACK;
        } else {
            return Trigger.ON_ATTACK;
        }
    }

    @Override
    public void apply(FightState state, String source) {

        if (didUse) {
            return;
        }

        String userStr = "";
        TeamAnimal user;

        if (source.equals("player")) {
            userStr = "Spieler";
            user = state.getPlayerTeam().get(0);
        } else {
            userStr = "Gegner";
            user = state.getEnemyTeam().get(0);
        }

        if (!isActive) {
            state.setLog(state.getLog() + "[VERSTECKEN] " + user.getEmoji() + " (" + userStr
                    + ") bekommt Angst und versteckt sich.\n");
            isActive = true;
            return;
        }

        if (source.equals("player")) {
            state.setIncomingDmg(0);
        } else {
            state.setOutgoingDmg(0);
        }

        isActive = false;
        didUse = true;

        state.setLog(state.getLog() + "[VERSTECKEN] " + user.getEmoji() + " (" + userStr
                + ") versteckt sich und greift aus dem Hinterhalt an.\n");

    }

}
