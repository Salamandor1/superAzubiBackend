package de.cancom.super_azubi_pets.Models.Skills.Skills;

import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Models.Skills.FightState;
import de.cancom.super_azubi_pets.Models.Skills.Skill;
import de.cancom.super_azubi_pets.Models.Skills.Trigger;

public class Undead implements Skill {

    public Undead(int level, int tier) {
    }

    @Override
    public String getName() {
        return "Untot";
    }

    @Override
    public String getDescription() {
        return "Belebt sich selbst einmal mit 50% ❤️ und 🗡️ wieder.";
    }

    @Override
    public Trigger getTrigger() {
        return Trigger.ON_OWN_DEATH;
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

        TeamAnimal original = state.getMap().get(user);

        user.setHealth(original.getHealth());
        user.setAttack(original.getAttack());
        user.setSkill(new None(0, 0));

        state.setLog(state.getLog() + "[UNTOT](" + user.getEmoji() + ", " + source + ") - wird wiederbelebt!\n");

    }

}
