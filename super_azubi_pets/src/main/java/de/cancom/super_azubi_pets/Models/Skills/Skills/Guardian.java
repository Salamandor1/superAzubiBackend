package de.cancom.super_azubi_pets.Models.Skills.Skills;

import java.util.List;

import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Models.Skills.FightState;
import de.cancom.super_azubi_pets.Models.Skills.Skill;
import de.cancom.super_azubi_pets.Models.Skills.Trigger;

public class Guardian implements Skill {

    private final int boost;

    public Guardian(int level, int tier) {
        this.boost = 1 + (level / 2) * (tier / 2);
    }

    @Override
    public String getName() {
        return "Beschützer";
    }

    @Override
    public String getDescription() {
        return "Erhöht das Leben aller verbleibenden Teammitglieder um " + boost + ".";
    }

    @Override
    public Trigger getTrigger() {
        return Trigger.ON_OWN_DEATH;
    }

    @Override
    public void apply(FightState state, String source, TeamAnimal user) {

        if (user.getHealth() > 0) {
            return;
        }

        List<TeamAnimal> targets;

        if (source.equals("player")) {
            source = "Player";
            targets = state.getPlayerTeam();
        } else {
            source = "Gegner";
            targets = state.getEnemyTeam();
        }

        for (TeamAnimal animal : targets) {
            if (animal == user) {
                continue;
            }
            if (animal.getHealth() <= 0) {
                continue;
            }
            animal.setHealth(animal.getHealth() + boost);
        }

        state.setLog(state.getLog() + "[BESCHÜTZER] (" + user.getEmoji() + ", " + source
                + ") - Gesundheit aller verbleibenden Teammitglieder um " + boost
                + " erhöht.\n");

    }
}
