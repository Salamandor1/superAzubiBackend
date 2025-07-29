package de.cancom.super_azubi_pets.Models.Skills;

public interface Skill {
    String getName();

    Trigger getTrigger();

    String getDescription();

    void apply(FightState state, String source);
}