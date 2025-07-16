package de.cancom.super_azubi_pets.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.cancom.super_azubi_pets.DTOs.LogQueryDTO;
import de.cancom.super_azubi_pets.DTOs.LogResponseDTO;
import de.cancom.super_azubi_pets.EmbeddedIds.LogID;
import de.cancom.super_azubi_pets.Services.LogService;

@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
    private LogService logService;

    // GET log by id
    @GetMapping("/{playerTeamID}/{npcTeamID}")
    public ResponseEntity<LogResponseDTO> getLogById(@PathVariable Long playerTeamID, @PathVariable Long npcTeamID) {
        return ResponseEntity.ok(logService.getLogByID(new LogQueryDTO(playerTeamID, npcTeamID)));
    }

    // DELETE log by id
    @DeleteMapping("{playerTeamID}/{npcTeamID}")
    public ResponseEntity<String> deleteLogByID(@PathVariable Long playerTeamID, @PathVariable Long npcTeamID) {
        logService.deleteLogByID(new LogID(playerTeamID, npcTeamID));
        return ResponseEntity.ok("Log with ID " + new LogID(playerTeamID, npcTeamID) + " was deleted.");
    }
}
