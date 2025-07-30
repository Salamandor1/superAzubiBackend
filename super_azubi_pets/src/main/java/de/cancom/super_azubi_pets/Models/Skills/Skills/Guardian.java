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
    public void apply(FightState state, String source) {

        if (source.equals("player")) {
            state.setLog(state.getLog() + apply(state.getPlayerTeam(), "Spieler"));
        } else {
            state.setLog(state.getLog() + apply(state.getEnemyTeam(), "Gegner"));
        }

    }

    private String apply(List<TeamAnimal> team, String source) {

        TeamAnimal skip = team.get(0);

        if (skip.getHealth() > 0) {
            return "";
        }

        for (TeamAnimal animal : team) {
            if (animal == skip) {
                continue;
            }
            animal.setHealth(animal.getHealth() + boost);
        }

        return "[BESCHÜTZER] (" + skip.getEmoji() + ", " + source
                + ") - Gesundheit aller verbleibenden Teammitglieder um " + boost
                + " erhöht.\n";

    }
}
