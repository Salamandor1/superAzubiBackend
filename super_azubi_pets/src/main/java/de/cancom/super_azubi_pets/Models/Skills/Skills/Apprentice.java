package de.cancom.super_azubi_pets.Models.Skills.Skills;

import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Models.Skills.FightState;
import de.cancom.super_azubi_pets.Models.Skills.Skill;
import de.cancom.super_azubi_pets.Models.Skills.Trigger;

public class Apprentice implements Skill {

    private final int boost;

    public Apprentice(int level, int tier) {
        switch (tier) {
            case 4, 5:
                this.boost = 2;
                break;
            case 6:
                this.boost = 3;
                break;
            default:
                this.boost = 1;
        }
    }

    @Override
    public String getName() {
        return "Lehrling";
    }

    @Override
    public String getDescription() {
        return "Erhöht alle Werte permanent um " + boost + ", aber auch das Level um 1.";
    }

    @Override
    public Trigger getTrigger() {
        return Trigger.ON_OWN_DEATH;
    }

    @Override
    public void apply(FightState state, String source) {
        if (source.equals("enemy")) {
            return;
        }
        TeamAnimal fightAnimal = state.getPlayerTeam().get(0);
        if (fightAnimal.getHealth() > 0 || fightAnimal.getLevel() >= 20) {
            return;
        }

        TeamAnimal origin = state.getMap().get(fightAnimal);
        origin.setLevel(origin.getLevel() + 1);
        origin.setHealth(origin.getHealth() + boost);
        origin.setAttack(origin.getAttack() + boost);
        state.setLog(state.getLog() + "[LEHRLING] (" + fightAnimal.getEmoji()
                + ", Spieler) - Level um 1 und alle Werte permanent um " + boost + " erhöht.\n");
        return;

    }
}