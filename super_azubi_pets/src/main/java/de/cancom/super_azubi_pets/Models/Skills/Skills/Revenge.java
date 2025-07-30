package de.cancom.super_azubi_pets.Models.Skills.Skills;

import java.util.List;

import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Models.Skills.FightState;
import de.cancom.super_azubi_pets.Models.Skills.Skill;
import de.cancom.super_azubi_pets.Models.Skills.Trigger;

public class Revenge implements Skill {

    private final int damage;

    public Revenge(int level, int tier) {
        this.damage = 5 + ((level / 4) * (tier / 2));
    }

    @Override
    public String getName() {
        return "Rache";
    }

    @Override
    public String getDescription() {
        return "Fügt einem zufälligen Gegner " + damage + " Schaden zu.";
    }

    @Override
    public Trigger getTrigger() {
        return Trigger.ON_ATTACK;
    }

    @Override
    public void apply(FightState state, String source) {
        String log = "[RACHE] (";
        if (source.equals("player")) {
            if (state.getEnemyTeam().size() < 2) {
                return;
            }
            log += state.getPlayerTeam().get(0).getEmoji() + ", Spieler) - fügt einem zufälligen Gegner (";
            log += apply(state.getEnemyTeam());
        } else {
            if (state.getPlayerTeam().size() < 2) {
                return;
            }
            log += state.getPlayerTeam().get(0).getEmoji() + ", Gegner) - fügt einem zufälligen Gegner (";
            log += apply(state.getPlayerTeam());
        }
        state.setLog(state.getLog() + log);
    }

    public String apply(List<TeamAnimal> team) {

        if (team.size() < 2) {
            return "";
        }

        int index = (int) (Math.random() * (team.size() - 1)) + 1;

        team.get(index).setHealth(team.get(index).getHealth() - damage);
        if (team.get(index).getHealth() <= 0) {
            return team.get(index).getEmoji() + ") " + damage + " Schaden zu. " + team.get(index).getEmoji()
                    + " wurde besiegt.";
        }

        return team.get(index).getEmoji() + ") " + damage + " Schaden zu.\n";

    }
}
