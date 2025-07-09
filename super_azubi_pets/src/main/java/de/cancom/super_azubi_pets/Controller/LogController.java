package de.cancom.super_azubi_pets.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.cancom.super_azubi_pets.DTOs.LogQueryDTO;
import de.cancom.super_azubi_pets.DTOs.LogResponseDTO;
import de.cancom.super_azubi_pets.Services.LogService;

@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
    private LogService logService;

    // Get log by id
    @GetMapping("/{playerTeamID}/{npcTeamID}")
    public ResponseEntity<LogResponseDTO> getLogById(@PathVariable Long playerTeamID, @PathVariable Long npcTeamID) {
        return ResponseEntity.ok(logService.getLogByID(new LogQueryDTO(playerTeamID, npcTeamID)));
    } 
}
