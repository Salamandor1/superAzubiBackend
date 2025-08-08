package de.cancom.super_azubi_pets.Models.Skills;

import de.cancom.super_azubi_pets.Models.TeamAnimal;

public interface Skill {
    String getName();

    Trigger getTrigger();

    String getDescription();

    void apply(FightState state, String source, TeamAnimal user);
}