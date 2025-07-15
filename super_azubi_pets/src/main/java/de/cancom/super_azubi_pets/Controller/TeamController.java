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

import de.cancom.super_azubi_pets.DTOs.CreateAndUpdateTeamDTO;
import de.cancom.super_azubi_pets.Models.Team;
import de.cancom.super_azubi_pets.Services.TeamService;

@RestController
@RequestMapping("/playerteam")
public class TeamController {

    @Autowired
    private TeamService teamService;

    // POST
    @PostMapping
    public ResponseEntity<CreateAndUpdateTeamDTO> createTeam(@RequestBody CreateAndUpdateTeamDTO dto) {
        teamService.createTeam(dto);
        return ResponseEntity.ok(dto);
    }

    // GET all
    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamByID(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.getTeamByID(id));
    }

    // PUT by ID
    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeamByID(@PathVariable Long id, @RequestBody CreateAndUpdateTeamDTO dto) {
        try {
            Team updatedTeam = teamService.updateTeamByID(id, dto);
            return ResponseEntity.ok(updatedTeam);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeamByID(@PathVariable Long id) {
        teamService.deleteTeamByID(id);
        return ResponseEntity.noContent().build();
    }
}
