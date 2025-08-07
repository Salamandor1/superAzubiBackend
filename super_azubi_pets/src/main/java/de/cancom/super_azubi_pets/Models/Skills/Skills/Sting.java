package de.cancom.super_azubi_pets.Models.Skills.Skills;

import java.util.List;

import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Models.Skills.FightState;
import de.cancom.super_azubi_pets.Models.Skills.Skill;
import de.cancom.super_azubi_pets.Models.Skills.Trigger;

public class Sting implements Skill {

    private final int dmg;

    public Sting(int level, int tier) {
        int dmg = (int) (Math.round((level * tier) / 5.0));
        if (dmg <= 0) {
            this.dmg = 1;
        } else {
            this.dmg = dmg;
        }
    }

    @Override
    public String getName() {
        return "Stich";
    }

    @Override
    public String getDescription() {
        return "Damages all enemy animals once the fight starts.";
    }

    @Override
    public Trigger getTrigger() {
        return Trigger.ON_GAME_START;
    }

    @Override
    public void apply(FightState state, String source, TeamAnimal user) {

        List<TeamAnimal> targets;
        String from;
        String newSource;
        String died = "";
        Skill skill;

        if (source.equals("player")) {
            from = "Spieler";
            newSource = "enemy";
            targets = state.getEnemyTeam();
        } else {
            from = "Gegner";
            newSource = "player";
            targets = state.getPlayerTeam();
        }

        String log = state.getLog();

        for (TeamAnimal target : targets) {
            target.setHealth(target.getHealth() - dmg);
            skill = target.getSkill();

            if (target.getHealth() <= 0) {
                died += target.getEmoji();
                if (skill.getTrigger() == Trigger.ON_OWN_DEATH) {
                    skill.apply(state, newSource, target);
                }
            }
        }

        if (!died.equals("")) {
            died += " wurde/n besiegt.\n";
        } else {
            died = "\n";
        }

        state.setLog(
                log + "[STICH](" + user.getEmoji() + ", " + from + ") - reduziert ❤️ aller Gegner um " + dmg
                        + ". " + died + "\n" + state.getLog());

    }

}
