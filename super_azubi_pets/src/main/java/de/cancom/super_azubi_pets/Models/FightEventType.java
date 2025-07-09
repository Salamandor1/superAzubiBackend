package de.cancom.super_azubi_pets.Models;

public enum FightEventType {

    ATTACK("Both most front animals fight each other, dealing damage simultaneously.") {
        @Override
        public String resolve(Team playerTeam, Team npcTeam) {
            TeamAnimal playerAnimal = playerTeam.getAnimals().get(0);
            TeamAnimal npcAnimal = npcTeam.getAnimals().get(0);

            String text = "";
            // playerAnimal attacks npcAnimal
            text = attack(playerAnimal, npcAnimal);

            // npcAnimal attack playerAnimal
            text += "\n" + attack(npcAnimal, playerAnimal);

            return text;
        } // resolve

        private String attack(TeamAnimal attacker, TeamAnimal defender) {
            String text = "";
            int dmg = attacker.getAttack();
            int health = defender.getHealth();
            defender.setHealth(health - dmg);
            text = attacker.getName() + "verursacht " + dmg + " an " + defender.getName() + ".";
            return text;
        } // attack

    }, // ATTACK

    DIE("If one of the frontmost animals health drops to 0 (or below), they will die.") {
        @Override
        public String resolve(Team playerTeam, Team npcTeam) {
            TeamAnimal playerAnimal = playerTeam.getAnimals().get(0);
            TeamAnimal npcAnimal = npcTeam.getAnimals().get(0);

            String text = "";

            // checks for playerAnimal
            if (isHealth0(playerAnimal)) {
                text += playerAnimal.getName() + " wurde besiegt.\n";
                playerAnimal = null;
            } // if

            // checks for npcAnimal
            if (isHealth0(npcAnimal)) {
                text += npcAnimal.getName() + " wurde besiegt.\n";
                npcAnimal = null;
            } // if

            return text;
        } // resolve

        private boolean isHealth0(TeamAnimal animal) {
            return animal.getHealth() <= 0;
        } // isHealth0
    }, // DIE

    MOVE("Changes position of one or more animals.") {
        @Override
        public String resolve(Team playerTeam, Team npcTeam) {
            // removes all null values in both ArrayLists
            // playerAnimals
            removeAnimals(playerTeam);

            // npcAnimals
            removeAnimals(npcTeam);

            return null;
        } // resolve

        private void removeAnimals(Team team) {
            for (TeamAnimal animal : team.getAnimals()) {
                if (animal.getHealth() <= 0) {
                    team.getAnimals().remove(animal);
                } // if
            } // for
        } // removeAnimals
    }, // MOVE

    SKILL("Uses the skill of an animal, if possible.") {
        @Override
        public String resolve(Team playerTeam, Team npcTeam) {
            // does nothing atm
            return null;
        } // resolve
    }, // SKILL

    END_FIGHT("Shows the final result of the fight and if the player has won or lost.") {
        @Override
        public String resolve(Team playerTeam, Team npcTeam) {
            int acp = playerTeam.getAnimals().size();
            int acn = npcTeam.getAnimals().size();

            // Always counts up the counter for rounds played
            playerTeam.setRounds(playerTeam.getRounds() + 1);

            // Tie
            if (acp > 0 && acn > 0) {
                return "Das Match ist unentschieden nach 20 Runden.";
            } // if
              // Win
            if (acp > 0) {
                playerTeam.setWins(playerTeam.getWins() + 1);
                return "Du hast das Match gewonnen.";
            }
            // Lose
            else {
                playerTeam.setHearts(playerTeam.getHearts() - 1);
                return "Du hast das Match verloren.";
            } // if-else

        } // resolve
    }, // END_FIGHT

    END_GAME("Checks if player has hearts left, otherwise gives impules to end the current game.") {
        @Override
        public String resolve(Team playerTeam, Team npcTeam) {
            if (playerTeam.getHearts() > 0) {
                return null;
            } else {
                return "Du hast keine Leben mehr übrig und verlierst das Spiel.";
            } // if-else
        } // resolve
    }; // END_GAME

    private FightEventType(String description) {
        this.description = description;
    } // constructor

    final public String description;

    public String getDescription() {
        return description;
    } // getDescription

    // Method to do the work
    public abstract String resolve(Team playerTeam, Team npcTeam);

} // enum : FightEventTypeService
