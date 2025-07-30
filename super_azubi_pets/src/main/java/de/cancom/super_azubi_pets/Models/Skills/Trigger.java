package de.cancom.super_azubi_pets.Models.Skills;

import java.util.List;

import de.cancom.super_azubi_pets.Models.TeamAnimal;

public enum Trigger {

    ON_GAME_START,
    ON_GAME_END,
    ON_ROUND_START,
    ON_ROUND_END,
    BEFORE_ATTACK {
        @Override
        public List<TeamAnimal> trim(List<TeamAnimal> team) {
            return List.of(team.get(0));
        }
    },
    ON_ATTACK {
        @Override
        public List<TeamAnimal> trim(List<TeamAnimal> team) {
            return List.of(team.get(0));
        }
    },
    AFTER_ATTACK {
        @Override
        public List<TeamAnimal> trim(List<TeamAnimal> team) {
            return List.of(team.get(0));
        }
    },
    ON_OWN_DEATH {
        @Override
        public List<TeamAnimal> trim(List<TeamAnimal> team) {
            return List.of(team.get(0));
        }
    },
    ON_FRIEND_DEATH,
    ON_ENEMY_DEATH,
    ON_HEALTH_THRESHOLD,
    ON_DAMAGE,
    NONE,

    ;

    public List<TeamAnimal> trim(List<TeamAnimal> team) {
        return team;
    }
}
