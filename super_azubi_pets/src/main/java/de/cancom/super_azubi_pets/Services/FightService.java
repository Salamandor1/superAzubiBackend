package de.cancom.super_azubi_pets.Services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.cancom.super_azubi_pets.EmbeddedIds.LogID;
import de.cancom.super_azubi_pets.Models.Animal;
import de.cancom.super_azubi_pets.Models.Fight;
import de.cancom.super_azubi_pets.Models.Game;
import de.cancom.super_azubi_pets.Models.Log;
import de.cancom.super_azubi_pets.Models.Team;
import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Models.Skills.FightState;
import de.cancom.super_azubi_pets.Models.Skills.Trigger;
import de.cancom.super_azubi_pets.Repositories.GameRepository;
import de.cancom.super_azubi_pets.Repositories.LogRepository;
import de.cancom.super_azubi_pets.Repositories.TeamRepository;

@Service
public class FightService {

    @Autowired
    private LogRepository logRepo;

    @Autowired
    private GameRepository gameRepo;

    @Autowired
    private TeamRepository teamRepo;

    @Autowired
    private AnimalService baseAnimalService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private GameService gameService;

    @Autowired
    private TeamService teamService;

    public FightService() {
    }

    public Log resolveFight(Long gameID) {
        String log = "";

        // Fetch Game
        Game game = gameRepo.findById(gameID).orElseThrow();
        Fight fight = new Fight(game);

        // Generate Teams
        fight.setPlayerTeam(fetchPlayerTeam(fight.getGame()));
        Game ghostGame = gameService.findGameByStatus(game);
        // System.out.println(ghostGame.getGameID());
        if (ghostGame != null && teamService.areEqual(game.getTeam(), ghostGame.getTeam())) {
            ghostGame = null;
        }
        if (ghostGame == null || didWin(ghostGame.getTeam().getAllAnimals())) {
            fight.setNpcTeam(generateNpcTeam(fight.getGame()));
            log += "Zufälliges Gegnerteam generiert.\n";
        } else {
            fight.setNpcTeam(ghostGame.getTeam());
            log += "Geistdaten eines anderen Spielers geladen.\n";
        }
        copyTeams(fight);

        // Init fight
        int round = 1;
        List<TeamAnimal> playerTeam = fight.getPlayerTeamAnimals();
        List<TeamAnimal> enemyTeam = fight.getEnemyTeamAnimals();
        FightState state = new FightState(game, playerTeam, enemyTeam);
        state.initMap(fight);
        removeDeadAnimals(playerTeam, enemyTeam);
        skillService.initSkills(playerTeam);
        skillService.initSkills(enemyTeam);

        // Trigger.ON_GAME_START
        skillService.triggerAllSkills(Trigger.ON_GAME_START, state, playerTeam, enemyTeam);
        log += state.getLog();

        while (true) {

            removeDeadAnimals(fight.getPlayerTeamAnimals(), fight.getEnemyTeamAnimals());
            if (isTied(playerTeam, enemyTeam)) {
                log += "Der Kampf ging unentschieden aus! Beide Teams haben keine kampffähigen Tiere mehr.";
                break;
            }
            if (didWin(enemyTeam)) {
                addWin(game);
                log += "Du hast den Kampf gewonnen!";
                break;
            }
            if (didLose(playerTeam)) {
                removeHeart(game);
                log += "Leider hast du dem Kampf verloren...";
                break;
            }
            if (round >= 21) {
                log += "Der Kampf ist unentschieden, da nach 20 Runden kein Gewinner ermittelt werden konnte.";
                break;
            }
            log += "--------------- Runde " + round + " ---------------\n";
            log += getCurrentTeamsDisplay(playerTeam, enemyTeam);
            // Trigger.ON_ROUND_START
            skillService.triggerAllSkills(Trigger.ON_ROUND_START, state, playerTeam, enemyTeam);
            log += state.getLog();
            log += attack(game, state) + "\n";
            round++;
            // Trigger.ON_ROUND_END
            skillService.triggerAllSkills(Trigger.ON_ROUND_END, state, playerTeam, enemyTeam);
            log += state.getLog();
        } // while

        // Update Game-Object and save game to database
        endFight(game);
        // Trigger.ON_GAME_END
        skillService.triggerAllSkills(Trigger.ON_GAME_END, state, playerTeam, enemyTeam);
        log += state.getLog();

        // Update Log-Object and save to database
        Log logObj = createLogObject(fight, log);
        return logRepo.save(logObj);
    }

    private Team fetchPlayerTeam(Game game) {
        Long playerTeamID = game.getTeam().getID();
        Team playerTeam = teamRepo.findById(playerTeamID).orElseThrow();
        return playerTeam;
    }

    private Team generateNpcTeam(Game game) {
        Team npcTeam = new Team();
        // set animal Count for npc team
        int animalCount = calculateAnimalCount(game.getRounds());

        // transform base animals to team animals
        List<Animal> randomAnimals = baseAnimalService.getRandomAnimals(game.getRounds(), animalCount);
        List<TeamAnimal> teamAnimals = transformAnimals(randomAnimals);

        // calculate xp for team
        int xp = calculateXP(game);

        // level up team animals
        levelUpTeam(teamAnimals, xp);

        // put team animals into the npc team
        putAnimalsToTeam(teamAnimals, npcTeam);

        return teamRepo.save(npcTeam);
    }

    private int calculateAnimalCount(int gameRound) {
        if (gameRound <= 1) {
            return 3;
        }
        if (gameRound == 2) {
            return 4;
        }
        return 5;
    }

    private List<TeamAnimal> transformAnimals(List<Animal> baseAnimals) {
        List<TeamAnimal> teamAnimals = new ArrayList<>();
        for (Animal animal : baseAnimals) {
            TeamAnimal teamAnimal = baseAnimalService.transformToTeamAnimal(animal);
            teamAnimals.add(teamAnimal);
        }
        return teamAnimals;
    }

    private int calculateXP(Game game) {
        // check for 0
        int countedRounds = game.getRounds() - 1;
        if (countedRounds <= 0) {
            return 0;
        }

        // base XP
        int baseXP = (int) (Math.round((1.7 * countedRounds) * (1.0 + (countedRounds / 200.0))));

        // bonus XP
        int wins = game.getWins();
        double winFactor = (double) wins / countedRounds;
        int bonusXP = (int) (Math.round(countedRounds * winFactor));

        return (baseXP + bonusXP);
    }

    private void levelUpTeam(List<TeamAnimal> animals, int xp) {
        for (int i = 0; i < xp; i++) {
            // if all animals are level 20 this loop is rendundant
            if (areAllAtMaxLevel(animals)) {
                break;
            }
            int animalIndex = (int) (Math.random() * animals.size());
            TeamAnimal teamAnimal = animals.get(animalIndex);
            // if animal is level 20, just continue and reinvest the xp
            if (teamAnimal.getLevel() >= 20) {
                i--;
                continue;
            }
            teamAnimal.levelUp();
        }
    }

    private boolean areAllAtMaxLevel(List<TeamAnimal> animals) {
        return animals.stream().allMatch(a -> a.getLevel() >= 20);
    }

    private void putAnimalsToTeam(List<TeamAnimal> animals, Team team) {
        switch (animals.size()) {
            case 5:
                team.setSlot4(animals.get(4));
            case 4:
                team.setSlot3(animals.get(3));
            case 3:
                team.setSlot2(animals.get(2));
            case 2:
                team.setSlot1(animals.get(1));
            case 1:
                team.setSlot0(animals.get(0));
        }
        teamRepo.save(team);
    }

    private void copyTeams(Fight fight) {
        for (TeamAnimal original : fight.getPlayerTeam().getAllAnimals()) {
            if (original == null) {
                continue;
            }
            TeamAnimal copy = new TeamAnimal(original);
            fight.getPlayerTeamAnimals().add(copy);
        }
        for (TeamAnimal original : fight.getNpcTeam().getAllAnimals()) {
            if (original == null) {
                continue;
            }
            TeamAnimal copy = new TeamAnimal(original);
            fight.getEnemyTeamAnimals().add(copy);
        }
    }

    private void removeDeadAnimals(List<TeamAnimal> playerTeam, List<TeamAnimal> enemyTeam) {
        playerTeam.removeIf(item -> item == null || item.getHealth() <= 0);
        enemyTeam.removeIf(item -> item == null || item.getHealth() <= 0);
    }

    private String attack(Game game, FightState state) {
        List<TeamAnimal> playerTeam = state.getPlayerTeam();
        List<TeamAnimal> enemyTeam = state.getEnemyTeam();
        String log = "";

        // team to animal
        TeamAnimal playerAnimal = playerTeam.get(0);
        TeamAnimal enemyAnimal = enemyTeam.get(0);

        // fetch attack value
        state.setOutgoingDmg(playerAnimal.getAttack());
        state.setIncomingDmg(enemyAnimal.getAttack());

        // Trigger.BEFORE_ATTACK
        skillService.checkTrigger(Trigger.BEFORE_ATTACK, playerAnimal, state, "player");
        skillService.checkTrigger(Trigger.BEFORE_ATTACK, enemyAnimal, state, "enemy");
        log += state.getLog();

        // Trigger.ON_ATTACK
        skillService.checkTrigger(Trigger.ON_ATTACK, playerAnimal, state, "player");
        skillService.checkTrigger(Trigger.ON_ATTACK, enemyAnimal, state, "enemy");

        // apply damage
        playerAnimal.setHealth(playerAnimal.getHealth() - state.getIncomingDmg());
        enemyAnimal.setHealth(enemyAnimal.getHealth() - state.getOutgoingDmg());
        log += playerAnimal.getEmoji() + playerAnimal.getName() + " (Spieler) verursacht " + state.getOutgoingDmg()
                + " Schaden an " + enemyAnimal.getEmoji() + enemyAnimal.getName()
                + " (Gegner).\n";
        log += enemyAnimal.getEmoji() + enemyAnimal.getName() + " (Gegner) verursacht " + state.getIncomingDmg()
                + " Schaden an "
                + playerAnimal.getEmoji() + playerAnimal.getName()
                + " (Spieler).\n";
        log += state.getLog();

        // Trigger.ON_DAMAGE
        if (state.getIncomingDmg() > 0) {
            skillService.checkTrigger(Trigger.ON_DAMAGE, playerAnimal, state, "player");
        }
        if (state.getOutgoingDmg() > 0) {
            skillService.checkTrigger(Trigger.ON_DAMAGE, enemyAnimal, state, "enemy");
        }
        log += state.getLog();

        // Trigger.AFTER_ATTACK
        skillService.checkTrigger(Trigger.AFTER_ATTACK, playerAnimal, state, "player");
        skillService.checkTrigger(Trigger.AFTER_ATTACK, enemyAnimal, state, "enemy");
        log += state.getLog();

        log += die(playerAnimal, state, "player");
        log += die(enemyAnimal, state, "enemy");

        return log + "\n";
    }

    private String die(TeamAnimal animal, FightState state, String who) {
        String from;
        if (who.equals("player")) {
            from = "Spieler";
        } else {
            from = "Gegner";
        }
        String log = "";
        if (animal.getHealth() <= 0) {
            log += animal.getEmoji() + " (" + from + ") wurde besiegt!\n";
            skillService.checkTrigger(Trigger.ON_OWN_DEATH, animal, state, who);
            log += state.getLog();
        }
        return log;
    }

    private boolean isTied(List<TeamAnimal> playerTeam, List<TeamAnimal> enemyTeam) {
        return didWin(enemyTeam) && didLose(playerTeam);
    }

    private boolean didWin(List<TeamAnimal> enemyTeam) {
        return enemyTeam.isEmpty();
    }

    private void addWin(Game game) {
        game.setWins(game.getWins() + 1);
    }

    private boolean didLose(List<TeamAnimal> playerTeam) {
        return playerTeam.isEmpty();
    }

    private void removeHeart(Game game) {
        game.setHearts(game.getHearts() - 1);
    }

    private void endFight(Game game) {
        game.setRound(game.getRounds() + 1);
        gameRepo.save(game);
    }

    private String getCurrentTeamsDisplay(List<TeamAnimal> playerTeam, List<TeamAnimal> enemyTeam) {
        String log = "";

        List<TeamAnimal> mirroredPlayerTeam = new ArrayList<>(playerTeam);
        Collections.reverse(mirroredPlayerTeam);

        log += getCurrentTeamDisplay(mirroredPlayerTeam);
        log += " vs. ";
        log += getCurrentTeamDisplay(enemyTeam);
        log += "\n";
        return log;
    }

    private String getCurrentTeamDisplay(List<TeamAnimal> team) {
        String log = "";
        for (TeamAnimal animal : team) {
            if (animal == null) {
                continue;
            }
            log += "[" + animal.getEmoji() + "(❤️" + animal.getHealth() + "/🗡️" + animal.getAttack() + ")]";
        }
        return log;
    }

    private Log createLogObject(Fight fight, String log) {
        Log logObj = new Log();
        Long playerTeamID = fight.getPlayerTeam().getID();
        Long npcTeamID = fight.getNpcTeam().getID();
        LogID logID = new LogID(playerTeamID, npcTeamID);
        logObj.setLogID(logID);
        logObj.setLog(log);
        return logObj;
    }
}
