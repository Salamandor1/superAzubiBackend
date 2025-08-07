package de.cancom.super_azubi_pets.Models.Skills.Skills;

import java.util.List;

import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Models.Skills.FightState;
import de.cancom.super_azubi_pets.Models.Skills.Skill;
import de.cancom.super_azubi_pets.Models.Skills.Trigger;

public class Charm implements Skill {

    private int charges;

    public Charm(int level, int tier) {
        switch (tier) {
            case 7:
                charges = 3;
                break;
            case 6, 5:
                charges = 2;
                break;
            default:
                charges = 1;
        }
    }

    public int getCharges() {
        return charges;
    }

    public void reduceCharges() {
        this.charges--;
    }

    @Override
    public String getName() {
        return "Charm";
    }

    @Override
    public String getDescription() {
        return "Leitet Angriff mit halber Stärke auf zufälligen Gegner weiter.";
    }

    @Override
    public Trigger getTrigger() {
        return Trigger.ON_ATTACK;
    }

    @Override
    public void apply(FightState state, String source, TeamAnimal user) {

        if (state.getPlayerTeam().get(0) == user) {
            if (state.getEnemyTeam().get(0).getSkill() instanceof Charm) {
                if (this.charges > 0 &&
                        ((Charm) state.getEnemyTeam().get(0).getSkill()).getCharges() > 0) {
                    applyBoth(state, user, state.getEnemyTeam().get(0));
                    return;
                }
            }
        }

        if (state.getEnemyTeam().get(0) == user) {
            if (state.getPlayerTeam().get(0).getSkill() instanceof Charm) {
                if (this.charges > 0 &&
                        ((Charm) state.getEnemyTeam().get(0).getSkill()).getCharges() > 0) {
                    applyBoth(state, state.getPlayerTeam().get(0), user);
                    return;
                }
            }
        }

        if (charges <= 0) {
            return;
        }

        List<TeamAnimal> targets;
        String from;
        String to;
        int dmg;
        String newSource;

        if (source.equals("player")) {
            if (state.getIncomingDmg() <= 0) {
                return;
            }
            targets = state.getEnemyTeam();
            from = "Spieler";
            to = "Gegner";
            newSource = "enemy";
            state.setOutgoingDmg((int) (Math.round(targets.get(0).getAttack() / 2.0)));
        } else {
            if (state.getOutgoingDmg() <= 0) {
                return;
            }
            targets = state.getPlayerTeam();
            from = "Gegner";
            to = "Spieler";
            newSource = "player";
            state.setIncomingDmg((int) (Math.round(targets.get(0).getAttack() / 2.0)));
        }

        if (targets.size() < 2) {
            return;
        } else {
            charges--;
        }

        int index = (int) (Math.random() * (targets.size() - 1)) + 1;
        TeamAnimal target = targets.get(index);

        Skill skill = target.getSkill();
        if (skill.getTrigger() == Trigger.BEFORE_ATTACK || skill.getTrigger() == Trigger.ON_ATTACK) {
            skill.apply(state, newSource, target);
        }

        if (source.equals("player")) {
            dmg = state.getOutgoingDmg();
        } else {
            dmg = state.getIncomingDmg();
        }

        target.setHealth(target.getHealth() - dmg);

        state.setLog(state.getLog() + "[CHARM](" + user.getEmoji() + ", " + from + ") - leitet " + dmg + " Schaden an "
                + target.getEmoji() + " (" + to + ") weiter.");

        if (skill.getTrigger() == Trigger.ON_DAMAGE || skill.getTrigger() == Trigger.AFTER_ATTACK) {
            state.setLog(state.getLog() + "\n");
            skill.apply(state, newSource, target);
        }

        state.setIncomingDmg(0);
        state.setOutgoingDmg(0);

        if (target.getHealth() <= 0) {
            state.setLog(state.getLog() + target.getEmoji() + " wurde besiegt.\n");
            if (skill.getTrigger() == Trigger.ON_OWN_DEATH) {
                skill.apply(state, newSource, target);
            }
        } else {
            state.setLog(state.getLog() + "\n");
        }

    }

    private void applyBoth(FightState state, TeamAnimal player, TeamAnimal enemy) {

        state.setOutgoingDmg((int) (Math.round(enemy.getAttack() / 2.0)));
        state.setIncomingDmg((int) (Math.round(player.getAttack() / 2.0)));

        List<TeamAnimal> playerTeam = state.getPlayerTeam();
        List<TeamAnimal> enemyTeam = state.getEnemyTeam();

        if (playerTeam.size() >= 2) {
            ((Charm) player.getSkill()).reduceCharges();
            int index = (int) (Math.random() * (enemyTeam.size() - 1)) + 1;
            TeamAnimal target = enemyTeam.get(index);
            Skill skill = target.getSkill();
            if (skill.getTrigger() == Trigger.BEFORE_ATTACK || skill.getTrigger() == Trigger.ON_ATTACK) {
                skill.apply(state, "enemy", target);
            }
            target.setHealth(target.getHealth() - state.getOutgoingDmg());
            state.setLog(state.getLog() + "[CHARM](" + player.getEmoji() + ", Spieler) - leitet "
                    + state.getOutgoingDmg() + " Schaden an " + target.getEmoji() + "(Gegner) weiter. ");
            if (target.getHealth() <= 0) {
                state.setLog(state.getLog() + target.getEmoji() + "wurde besiegt.\n");
                if (skill.getTrigger() == Trigger.ON_OWN_DEATH) {
                    skill.apply(state, "enemy", target);
                }
            } else {
                state.setLog(state.getLog() + "\n");
                if (skill.getTrigger() == Trigger.ON_DAMAGE) {
                    skill.apply(state, "enemy", target);
                }
            }
        }

        state.setOutgoingDmg(0);

        if (enemyTeam.size() >= 2) {
            ((Charm) enemy.getSkill()).reduceCharges();
            int index = (int) (Math.random() * (playerTeam.size() - 1)) + 1;
            TeamAnimal target = playerTeam.get(index);
            Skill skill = target.getSkill();
            if (skill.getTrigger() == Trigger.BEFORE_ATTACK || skill.getTrigger() == Trigger.ON_ATTACK) {
                skill.apply(state, "player", target);
            }
            target.setHealth(target.getHealth() - state.getIncomingDmg());
            state.setLog(state.getLog() + "[CHARM](" + player.getEmoji() + ", Gegner) - leitet "
                    + state.getIncomingDmg() + " Schaden an " + target.getEmoji() + "(Spieler) weiter. ");
            if (target.getHealth() <= 0) {
                state.setLog(state.getLog() + target.getEmoji() + "wurde besiegt.\n");
                if (skill.getTrigger() == Trigger.ON_OWN_DEATH) {
                    skill.apply(state, "player", target);
                }
            } else {
                state.setLog(state.getLog() + "\n");
                if (skill.getTrigger() == Trigger.ON_DAMAGE) {
                    skill.apply(state, "player", target);
                }
            }
        }

        state.setIncomingDmg(0);

    }

}
