package de.cancom.super_azubi_pets.Services;

import java.util.ArrayList;
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
import de.cancom.super_azubi_pets.Repositories.AnimalRepository;
import de.cancom.super_azubi_pets.Repositories.GameRepository;
import de.cancom.super_azubi_pets.Repositories.LogRepository;
import de.cancom.super_azubi_pets.Repositories.TeamRepository;

@Service
public class FightService {

    @Autowired
    LogRepository logRepo;

    @Autowired
    GameRepository gameRepo;

    @Autowired
    TeamRepository teamRepo;

    @Autowired
    AnimalRepository baseAnimalRepo;

    @Autowired
    AnimalService baseAnimalService;

    private Game game;
    private Fight fight = new Fight();
    private List<TeamAnimal> playerTeam = new ArrayList<>();
    private List<TeamAnimal> enemyTeam = new ArrayList<>();
    private Log log = new Log();

    public FightService() {
    }

    public FightService(Fight fight) {
        this.fight = fight;
    }

    public void setFight(Fight fight) {
        this.fight = fight;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Fight getFight() {
        return fight;
    }

    public Game getGame() {
        return game;
    }

    public Log resolveFight(Long gameID) {
        String log = "";

        // Fetch Game
        setGame(gameRepo.findById(gameID).orElseThrow());

        // Generate Teams
        fight = new Fight();
        fight.setPlayerTeam(fetchPlayerTeam());
        fight.setNpcTeam(generateNpcTeam());
        copyTeams();

        // Fight
        int round = 1;
        removeDeadAnimals();

        while (true) {
            log += "--------------- Runde " + round + " ---------------\n";
            log += attack() + "\n";
            removeDeadAnimals();
            if (isTied()) {
                log += "Der Kampf ging unentschieden aus! Beide Teams haben keine kampffähigen Tiere mehr.";
            }
            if (didWin()) {
                log += "Du hast den Kampf gewonnen!";
                break;
            }
            if (didLose()) {
                log += "Leider hast du dem Kampf verloren...";
                break;
            }
            if (round >= 20) {
                log += "Der Kampf ist unentschieden, da nach 20 Runden kein Gewinner ermittelt werden konnte.";
            }
            round++;
        } // while

        // Update Game-Object and save game to database
        endFight();

        // Update Log-Object and save to database
        updateLogObject(log);
        return logRepo.save(this.log);
    }

    private Team fetchPlayerTeam() {
        Long playerTeamID = game.getTeam().getID();
        Team playerTeam = teamRepo.findById(playerTeamID).orElseThrow();
        return playerTeam;
    }

    private Team generateNpcTeam() {
        Team npcTeam = new Team();
        // set animal Count for npc team
        int animalCount = calculateAnimalCount(game.getRound());

        // transform base animals to team animals
        List<Animal> randomAnimals = baseAnimalRepo.findRandomAnimals(animalCount);
        List<TeamAnimal> teamAnimals = transformAnimals(randomAnimals);

        // calculate xp for team
        int xp = calculateXP();

        // level up team animals
        levelUpTeam(teamAnimals, xp);

        // put team animals into the npc team
        putAnimalsToTeam(teamAnimals, npcTeam);

        return teamRepo.save(npcTeam);
    }

    private int calculateAnimalCount(int gameRound) {
        if (gameRound == 1) {
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

    private int calculateXP() {
        double lifeFactor = (double) game.getHearts() / 10.0;
        double winFactor = (double) game.getWins() / game.getRound();
        double combinedFactor = (winFactor * 0.7) + (lifeFactor * 0.3);
        int xp = game.getRound() + (int) Math.round(combinedFactor * 20.0);
        return xp;
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

    private void copyTeams() {
        for (TeamAnimal original : fight.getPlayerTeam().getAllAnimals()) {
            if (original == null) {
                continue;
            }
            TeamAnimal copy = new TeamAnimal(original);
            playerTeam.add(copy);
            System.out.println("Animal added to player: " + copy.getName() + "/" + copy.getHealth());
            System.out.println(playerTeam.size());
        }
        for (TeamAnimal original : fight.getNpcTeam().getAllAnimals()) {
            if (original == null) {
                continue;
            }
            TeamAnimal copy = new TeamAnimal(original);
            enemyTeam.add(copy);
            System.out.println("Animal added to enemy: " + copy.getName() + "/" + copy.getHealth());
            System.out.println(enemyTeam.size());

        }
    }

    private void removeDeadAnimals() {
        playerTeam.removeIf(item -> item == null || item.getHealth() <= 0);
        enemyTeam.removeIf(item -> item == null || item.getHealth() <= 0);
    }

    private String attack() {
        // fetch animals
        TeamAnimal playerAnimal = playerTeam.get(0);
        TeamAnimal npcAnimal = enemyTeam.get(0);
        // fetch attack value
        int playerAttack = playerAnimal.getAttack();
        int npcAttack = npcAnimal.getAttack();

        // apply damage
        playerAnimal.setHealth(playerAnimal.getHealth() - npcAttack);
        npcAnimal.setHealth(npcAnimal.getHealth() - playerAttack);

        String log = "";
        log += playerAnimal.getName() + " (Spieler) verursacht " + playerAttack + " Schaden an " + npcAnimal.getName()
                + " (Gegner). ";
        log += die(npcAnimal) + "\n";
        log += npcAnimal.getName() + " (Gegner) verursacht " + npcAttack + " Schaden an " + playerAnimal.getName()
                + " (Spieler). ";
        log += die(playerAnimal);
        return log;
    }

    private String die(TeamAnimal animal) {
        String log = "";
        if (animal.getHealth() <= 0) {
            log = animal.getName() + " wurde besiegt!";
        }
        return log;
    }

    private boolean isTied() {
        return didWin() && didLose();
    }

    private boolean didWin() {
        boolean didWin = enemyTeam.isEmpty();
        if (didWin) {
            game.setWins(game.getWins() + 1);
        }
        return didWin;
    }

    private boolean didLose() {
        boolean didLose = playerTeam.isEmpty();
        if (didLose) {
            game.setHearts(game.getHearts() - 1);
        }
        return didLose;
    }

    private void endFight() {
        game.setRound(game.getRound() + 1);
        gameRepo.save(game);
    }

    private void updateLogObject(String log) {
        Long playerTeamID = fight.getPlayerTeam().getID();
        Long npcTeamID = fight.getNpcTeam().getID();
        LogID logID = new LogID(playerTeamID, npcTeamID);
        this.log.setLogID(logID);
        this.log.setLog(log);
    }
}
