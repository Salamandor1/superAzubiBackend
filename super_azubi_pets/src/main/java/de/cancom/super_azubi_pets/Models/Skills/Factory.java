package de.cancom.super_azubi_pets.Models.Skills;

import java.util.Map;
import java.util.function.BiFunction;

import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Models.Skills.Skills.Annoying;
import de.cancom.super_azubi_pets.Models.Skills.Skills.Apprentice;
import de.cancom.super_azubi_pets.Models.Skills.Skills.Block;
import de.cancom.super_azubi_pets.Models.Skills.Skills.Charm;
import de.cancom.super_azubi_pets.Models.Skills.Skills.Courage;
import de.cancom.super_azubi_pets.Models.Skills.Skills.Feed;
import de.cancom.super_azubi_pets.Models.Skills.Skills.Guardian;
import de.cancom.super_azubi_pets.Models.Skills.Skills.Hide;
import de.cancom.super_azubi_pets.Models.Skills.Skills.Membrane;
import de.cancom.super_azubi_pets.Models.Skills.Skills.None;
import de.cancom.super_azubi_pets.Models.Skills.Skills.Rage;
import de.cancom.super_azubi_pets.Models.Skills.Skills.Revenge;
import de.cancom.super_azubi_pets.Models.Skills.Skills.Shield;
import de.cancom.super_azubi_pets.Models.Skills.Skills.Sting;
import de.cancom.super_azubi_pets.Models.Skills.Skills.Supply;
import de.cancom.super_azubi_pets.Models.Skills.Skills.Thorns;
import de.cancom.super_azubi_pets.Models.Skills.Skills.Trample;
import de.cancom.super_azubi_pets.Models.Skills.Skills.Undead;
import de.cancom.super_azubi_pets.Models.Skills.Skills.Stupid;

public class Factory {

    private static final Map<String, BiFunction<Integer, Integer, Skill>> SKILL_MAP = Map.ofEntries(
            Map.entry("BLOCK", Block::new),
            Map.entry("SCHILD", Shield::new),
            Map.entry("RAGE", Rage::new),
            Map.entry("BESCHÜTZER", Guardian::new),
            Map.entry("LEHRLING", Apprentice::new),
            Map.entry("RACHE", Revenge::new),
            Map.entry("MUT", Courage::new),
            Map.entry("NONE", None::new),
            Map.entry("VERSTECKEN", Hide::new),
            Map.entry("DORNEN", Thorns::new),
            Map.entry("STICH", Sting::new),
            Map.entry("TRAMPEL", Trample::new),
            Map.entry("NERVTÖTER", Annoying::new),
            Map.entry("MEMBRAN", Membrane::new),
            Map.entry("CHARM", Charm::new),
            Map.entry("VORRAT", Supply::new),
            Map.entry("FÜTTERN", Feed::new),
            Map.entry("UNFÄHIG", Stupid::new),
            Map.entry("UNTOT", Undead::new));

    public static Skill createSkill(String skill, TeamAnimal user) {
        skill = trim(skill);
        BiFunction<Integer, Integer, Skill> constructor = SKILL_MAP.get(skill);
        if (constructor != null) {
            return constructor.apply(user.getLevel(), user.getTier());
        } else {
            return new None(user.getLevel(), user.getTier());
        }
    }

    public static String trim(String skill) {
        int start = skill.indexOf('[');
        int end = skill.indexOf(']');
        return skill.substring(start + 1, end);
    }

}
