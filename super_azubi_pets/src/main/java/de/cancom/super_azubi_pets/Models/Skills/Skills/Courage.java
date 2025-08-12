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
<<<<<<< HEAD
    public void apply(FightState state, String source, TeamAnimal user) {

        if (source.equals("player")) {
            source = "Spieler";
            for (TeamAnimal animal : state.getPlayerTeam()) {
                animal.setAttack(animal.getAttack() + tier);
            }
        } else {
            source = "Gegner";
            for (TeamAnimal animal : state.getEnemyTeam()) {
                animal.setAttack(animal.getAttack() + tier);
            }
        }
        state.setLog(state.getLog() + "[MUT](" + user.getEmoji() + ", " + source
                + ") - erhöht den Angriff aller Teammitglieder um " + tier + ".\n");
=======
    public void apply(FightState state, String source) {

        String emoji = "";

        if (source.equals("player")) {
            for (TeamAnimal animal : state.getPlayerTeam()) {
                animal.setAttack(animal.getAttack() + tier);
                if (animal.getSkill() == this && emoji.equals("")) {
                    emoji += animal.getEmoji();
                    animal.setSkill(new None(animal.getLevel(), animal.getTier()));
                }
            }
            state.setLog(state.getLog() + "[MUT] (" + emoji + ", Spieler) - ");
        } else {
            for (TeamAnimal animal : state.getEnemyTeam()) {
                animal.setAttack(animal.getAttack() + tier);
                if (animal.getSkill() == this && emoji.equals("")) {
                    emoji += animal.getEmoji();
                    animal.setSkill(new None(animal.getLevel(), animal.getTier()));
                }
            }
            state.setLog(state.getLog() + "[MUT] (" + emoji + ", Gegner) - ");
        }
        state.setLog(state.getLog() + "erhöht den Angriff aller Teammitglieder um " + tier + ".\n");
>>>>>>> feat_online
    }
}
