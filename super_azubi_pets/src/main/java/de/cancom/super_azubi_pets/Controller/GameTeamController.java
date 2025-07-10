package de.cancom.super_azubi_pets.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.cancom.super_azubi_pets.DTOs.CreateAndUpdateGameTeamDTO;
import de.cancom.super_azubi_pets.Models.GameTeam;
import de.cancom.super_azubi_pets.Services.GameTeamService;

@RestController
@RequestMapping("/playerteam")
public class GameTeamController {

    @Autowired
    GameTeamService teamService;

    // POST
    @PostMapping
    public ResponseEntity<CreateAndUpdateGameTeamDTO> createTeam(@RequestBody CreateAndUpdateGameTeamDTO dto) {
        teamService.createTeam(dto);
        return ResponseEntity.ok(dto);
    }

    // GET all
    @GetMapping
    public ResponseEntity<List<GameTeam>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<GameTeam> getTeamByID(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.getTeamByID(id));
    }

    // PUT by ID
    @PutMapping("/{id}")
    public ResponseEntity<GameTeam> updateTeamByID(@PathVariable Long id, @RequestBody CreateAndUpdateGameTeamDTO dto) {
        try {
            GameTeam updatedTeam = teamService.updateTeamByID(id, dto);
            return ResponseEntity.ok(updatedTeam);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
