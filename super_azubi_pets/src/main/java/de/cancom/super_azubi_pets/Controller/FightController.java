package de.cancom.super_azubi_pets.Controller;

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

import de.cancom.super_azubi_pets.DTOs.CreateAndUpdateFightDTO;
import de.cancom.super_azubi_pets.Models.Fight;
import de.cancom.super_azubi_pets.Services.FightService;

@RestController
@RequestMapping("/fights")
public class FightController {

    @Autowired
    private FightService fightService;

    // Create
    @PostMapping
    public ResponseEntity<Fight> createFight(@RequestBody CreateAndUpdateFightDTO dto) {
        Fight newFight = fightService.createFight(dto);
        return ResponseEntity.ok(newFight);
    }

    // // Create & Resolve fight
    // @PostMapping("/resolve/")
    // public ResponseEntity<LogResponseDTO> createAndResolveFight(@RequestBody
    // LogQueryDTO dto) {
    // String log = fightService.resolveFight(dto);
    // LogResponseDTO logResponse = new LogResponseDTO(log);
    // return ResponseEntity.ok(logResponse);
    // }

    // Get all fights
    @GetMapping
    public ResponseEntity<List<Fight>> getAllFights() {
        return ResponseEntity.ok(fightService.getAllFights());
    }

    // Get fight by ID
    @GetMapping("/{id}")
    public ResponseEntity<Fight> getFightById(@PathVariable Long id) {
        return fightService.getFightById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Fight> updateFight(@PathVariable Long id, @RequestBody CreateAndUpdateFightDTO dto) {
        Fight updatedFight = fightService.updateFight(id, dto);
        return ResponseEntity.ok(updatedFight);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFight(@PathVariable Long id) {
        fightService.deleteFight(id);
        return ResponseEntity.noContent().build();
    }
}
