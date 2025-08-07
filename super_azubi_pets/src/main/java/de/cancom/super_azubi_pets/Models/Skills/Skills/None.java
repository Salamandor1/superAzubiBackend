package de.cancom.super_azubi_pets.Models.Skills.Skills;

import de.cancom.super_azubi_pets.Models.Skills.FightState;
import de.cancom.super_azubi_pets.Models.Skills.Skill;
import de.cancom.super_azubi_pets.Models.Skills.Trigger;

public class None implements Skill {

    public None(int level, int tier) {
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public Trigger getTrigger() {
        return Trigger.NONE;
    }

    @Override
    public void apply(FightState state, String source) {
    }
}
