package Archive;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fight-events")
public class FightEventController {

    @Autowired
    private FightEventService fightEventService;

    @GetMapping
    public List<FightEvent> getAllFightEvents() {
        return fightEventService.getAllFightEvents();
    }

    @GetMapping("/{fightId}/{index}")
    public ResponseEntity<FightEvent> getFightEventById(@PathVariable Long fightId, @PathVariable Long index) {
        FightEventId id = new FightEventId(fightId, index);
        return fightEventService.getFightEventById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public FightEvent createFightEvent(@RequestBody CreateAndUpdateFightEventDTO dto) {
        return fightEventService.createFightEvent(dto);
    }

    @PutMapping
    public FightEvent updateFightEvent(@RequestBody CreateAndUpdateFightEventDTO dto) {
        return fightEventService.updateFightEvent(dto);
    }

    @DeleteMapping("/{fightId}/{index}")
    public ResponseEntity<Void> deleteFightEvent(@PathVariable Long fightId, @PathVariable Long index) {
        FightEventId id = new FightEventId(fightId, index);
        fightEventService.deleteFightEvent(id);
        return ResponseEntity.noContent().build();
    }
}
