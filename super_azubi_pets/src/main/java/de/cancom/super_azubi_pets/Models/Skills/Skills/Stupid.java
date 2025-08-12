package de.cancom.super_azubi_pets.Models.Skills.Skills;

import de.cancom.super_azubi_pets.Models.Skills.Skill;
import de.cancom.super_azubi_pets.Models.Skills.Trigger;
import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Models.Skills.FightState;


public class Stupid implements Skill {
    
    public Stupid(int level, int tier) {
        // Constructor logic if needed
    }

    @Override
    public String getName() {
        return "Stupid";
    }

    @Override
    public String getDescription() {
        return "Damages itself after every attack";
    }

    @Override
    public Trigger getTrigger() {
        return Trigger.AFTER_ATTACK;
    }

    @Override
    public void apply(FightState state, String source, TeamAnimal user) {
        
        int newHealth = user.getHealth() - 2;
        user.setHealth(newHealth);
        state.setLog(state.getLog() + "[UNFÄHIG] (" + user.getEmoji() + ") verliert 2 Leben durch dummheit.\n");

    }
}
