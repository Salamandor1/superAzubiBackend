package de.cancom.super_azubi_pets.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Models.Skills.Factory;
import de.cancom.super_azubi_pets.Models.Skills.FightState;
import de.cancom.super_azubi_pets.Models.Skills.Skill;
import de.cancom.super_azubi_pets.Models.Skills.Trigger;

@Service
public class SkillService {

    public void initSkills(List<TeamAnimal> team) {
        for (TeamAnimal animal : team) {
            if (hasSkill(animal)) {
                animal.setSkill(getSkill(animal));
            }
        }
    }

    public void checkSkills(Trigger trigger, FightState state) {
        List<TeamAnimal> toCheckPlayerTeam = trigger.trim(state.getPlayerTeam());
        List<TeamAnimal> toCheckEnemyTeam = trigger.trim(state.getEnemyTeam());
        for (TeamAnimal animal : toCheckPlayerTeam) {
            if (hasSkill(animal) && animal.getSkill() != null && animal.getSkill().getTrigger() == trigger) {
                animal.getSkill().apply(state, "player");
            }
        }
        for (TeamAnimal animal : toCheckEnemyTeam) {
            if (hasSkill(animal) && animal.getSkill().getTrigger() == trigger) {
                animal.getSkill().apply(state, "enemy");
            }
        }
    }

    public Skill getSkill(TeamAnimal animal) {
        return Factory.createSkill(animal.getSkillDescription(), animal);
    }

    public boolean hasSkill(TeamAnimal animal) {
        return animal.getSkillDescription().length() > 3;
    }

}
