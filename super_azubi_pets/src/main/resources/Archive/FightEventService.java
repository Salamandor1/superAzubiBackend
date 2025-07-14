package Archive;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.cancom.super_azubi_pets.Models.Fight;
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

}
