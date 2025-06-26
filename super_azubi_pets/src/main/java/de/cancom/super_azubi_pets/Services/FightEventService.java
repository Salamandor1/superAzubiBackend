package de.cancom.super_azubi_pets.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.cancom.super_azubi_pets.DTOs.CreateAndUpdateFightEventDTO;
import de.cancom.super_azubi_pets.EmbeddedIds.FightEventId;
import de.cancom.super_azubi_pets.Models.Fight;
import de.cancom.super_azubi_pets.Models.FightEvent;
import de.cancom.super_azubi_pets.Models.FightEventType;
import de.cancom.super_azubi_pets.Models.Team;
import de.cancom.super_azubi_pets.Repositories.FightEventRepository;
import de.cancom.super_azubi_pets.Repositories.FightRepository;

@Service
public class FightEventService {

    @Autowired
    private FightEventRepository fightEventRepo;

    @Autowired
    private FightRepository fightRepo;

    public List<FightEvent> getAllFightEvents() {
        return fightEventRepo.findAll();
    }

    public Optional<FightEvent> getFightEventById(FightEventId id) {
        return fightEventRepo.findById(id);
    }

    public FightEvent createFightEvent(CreateAndUpdateFightEventDTO dto) {
        Fight fight = fightRepo.findById(dto.getFightId())
                .orElseThrow(() -> new RuntimeException("Fight with id " + dto.getFightId() + " not found"));

        FightEventId id = new FightEventId(dto.getFightId(), dto.getIndex());

        FightEvent fightEvent = new FightEvent();
        fightEvent.setId(id);
        fightEvent.setFight(fight);
        fightEvent.setEvent(dto.getEvent());

        return fightEventRepo.save(fightEvent);
    }

    public FightEvent updateFightEvent(CreateAndUpdateFightEventDTO dto) {
        FightEventId id = new FightEventId(dto.getFightId(), dto.getIndex());
        FightEvent fightEvent = fightEventRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("FightEvent not found"));

        fightEvent.setEvent(dto.getEvent());
        return fightEventRepo.save(fightEvent);
    }

    public void deleteFightEvent(FightEventId id) {
        if (fightEventRepo.existsById(id)) {
            fightEventRepo.deleteById(id);
        } else {
            throw new RuntimeException("FightEvent not found");
        }
    }

    // under construction
    // actual fight-logic
    public String resolveFightEvent(Fight fight) {
        Team playerTeam = fight.getTeam1();
        Team npcTeam = fight.getTeam2();

        // repeats logic until a winner is declared or round 20 is reached
        int round = 1;
        FightEventType event;
        String log = "";
        while (playerTeam.getAnimals().size() != 0 && npcTeam.getAnimals().size() != 0 && round <= 20) {
            event = FightEventType.ATTACK;
            log += event.resolve(playerTeam, npcTeam) + "\n";
            event = FightEventType.DIE;
            log += event.resolve(playerTeam, npcTeam) + "\n";
            event = FightEventType.MOVE;
            log += event.resolve(playerTeam, npcTeam) + "\n";
            round++;
        } // while

        event = FightEventType.END_FIGHT;
        log += event.resolve(playerTeam, npcTeam) + "\n";
        event = FightEventType.END_GAME;
        log += event.resolve(playerTeam, npcTeam);
        return log;
    } // resolveFightEvent
}
