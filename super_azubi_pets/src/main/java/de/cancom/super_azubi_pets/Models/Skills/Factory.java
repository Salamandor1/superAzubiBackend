package de.cancom.super_azubi_pets.Models.Skills;

import java.util.Map;
import java.util.function.BiFunction;

import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Models.Skills.Skills.Block;
import de.cancom.super_azubi_pets.Models.Skills.Skills.Shield;

public class Factory {

    private static final Map<String, BiFunction<Integer, Integer, Skill>> SKILL_MAP = Map.of(
            "BLOCK", Block::new,
            "SCHILD", Shield::new);

    public static Skill createSkill(String skill, TeamAnimal user) {
        skill = trim(skill);
        BiFunction<Integer, Integer, Skill> constructor = SKILL_MAP.get(skill);
        if (constructor != null) {
            return constructor.apply(user.getLevel(), user.getTier());
        }
        throw new IllegalArgumentException("Unknown Skill: " + skill);
    }

    public static String trim(String skill) {
        int start = skill.indexOf('[');
        int end = skill.indexOf(']');
        return skill.substring(start + 1, end);
    }

}
