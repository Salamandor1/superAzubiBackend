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

    public Skill getSkill(TeamAnimal animal) {
        return Factory.createSkill(animal.getSkillDescription(), animal);
    }

    public boolean hasSkill(TeamAnimal animal) {
        return animal.getSkillDescription().length() > 3;
    }

    public void checkTrigger(Trigger trigger, TeamAnimal user, FightState state, String source) {
        Skill skill = user.getSkill();
        if (skill.getTrigger() == trigger && skill != null) {
            skill.apply(state, source, user);
        }
    }

    public void triggerAllSkills(Trigger trigger, FightState state, List<TeamAnimal> playerTeam,
            List<TeamAnimal> enemyTeam) {
        for (TeamAnimal user : playerTeam) {
            checkTrigger(trigger, user, state, "player");
        }
        for (TeamAnimal user : enemyTeam) {
            checkTrigger(trigger, user, state, "enemy");
        }
    }

}
