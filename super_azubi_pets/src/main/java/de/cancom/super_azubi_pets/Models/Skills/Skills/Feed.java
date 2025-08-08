package de.cancom.super_azubi_pets.Models.Skills.Skills;

import java.util.List;

import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Models.Skills.FightState;
import de.cancom.super_azubi_pets.Models.Skills.Skill;
import de.cancom.super_azubi_pets.Models.Skills.Trigger;

public class Feed implements Skill {

    private final int tier;

    public Feed(int level, int tier) {
        this.tier = tier;
    }

    @Override
    public String getName() {
        return "Füttern";
    }

    @Override
    public String getDescription() {
        return "Gibt einem zufälligen Freund einen Apfel und erhöht dessen ❤️ dauerhaft.";
    }

    @Override
    public Trigger getTrigger() {
        return Trigger.ON_OWN_DEATH;
    }

    @Override
    public void apply(FightState state, String source, TeamAnimal user) {

        List<TeamAnimal> targets;
        TeamAnimal target;

        if (source.equals("player")) {
            source = "Spieler";
            targets = state.getPlayerTeam();
        } else {
            source = "Gegner";
            targets = state.getEnemyTeam();
        }

        if (user.getHealth() > 0) {
            return;
        }

        if (targets.size() < 2) {
            return;
        }

        do {
            int index = (int) (Math.random() * targets.size());
            target = targets.get(index);
        } while (target != user);

        if (target.getHealth() > 0) {
            target.setHealth(target.getHealth() + tier);
        }

        state.setLog(state.getLog() + "[FÜTTERN](" + user.getEmoji() + ", " + source + ") - füttert "
                + target.getEmoji() + " und erhöht dessen ❤️ dauerhaft um " + tier + ".\n");

        if (!source.equals("Spieler")) {
            return;
        } else {
            TeamAnimal original = state.getMap().get(target);
            original.setHealth(original.getHealth() + tier);
        }

    }

}
