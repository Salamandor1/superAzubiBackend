package de.cancom.super_azubi_pets.Models.Skills.Skills;

import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Models.Skills.FightState;
import de.cancom.super_azubi_pets.Models.Skills.Skill;
import de.cancom.super_azubi_pets.Models.Skills.Trigger;

public class Courage implements Skill {

    private final int tier;

    public Courage(int level, int tier) {
        this.tier = tier;
    }

    @Override
    public String getName() {
        return "Mut";
    }

    @Override
    public String getDescription() {
        return "Erhöht den Angriff aller tiere um " + tier;
    }

    @Override
    public Trigger getTrigger() {
        return Trigger.ON_GAME_START;
    }

    @Override
    public void apply(FightState state, String source) {

        if (source.equals("player")) {
            for (TeamAnimal animal : state.getPlayerTeam()) {
                animal.setAttack(animal.getAttack() + tier);
            }
            state.setLog(state.getLog() + "[MUT] (" + state.getPlayerTeam().get(0).getEmoji() + ", Spieler) - ");
        } else {
            for (TeamAnimal animal : state.getEnemyTeam()) {
                animal.setAttack(animal.getAttack() + tier);
            }
            state.setLog(state.getLog() + "[MUT] (" + state.getEnemyTeam().get(0).getEmoji() + ", Gegner) - ");
        }
        state.setLog(state.getLog() + " erhöht den Angriff aller Teammitglieder um " + tier + ".");
    }
}
