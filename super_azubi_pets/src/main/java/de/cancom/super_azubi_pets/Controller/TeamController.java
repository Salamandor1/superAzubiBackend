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
import de.cancom.super_azubi_pets.DTOs.TeamResponseDTO;
import de.cancom.super_azubi_pets.DTOs.TeamUpdateSlotDTO;
import de.cancom.super_azubi_pets.Models.Team;
import de.cancom.super_azubi_pets.Services.TeamService;

@RestController
@RequestMapping("/playerteam")
public class TeamController {

    @Autowired
    private TeamService teamService;

    // POST
    @PostMapping
    public ResponseEntity<TeamResponseDTO> createTeam(@RequestBody CreateAndUpdateTeamDTO dto) {
        Team team = teamService.createTeam(dto);
        return ResponseEntity.ok(teamService.convertToDTO(team));
    }

    // GET all
    @GetMapping
    public ResponseEntity<List<TeamResponseDTO>> getAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teamService.convertToDTO(teams));
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<TeamResponseDTO> getTeamByID(@PathVariable Long id) {
        Team team = teamService.getTeamByID(id);
        return ResponseEntity.ok(teamService.convertToDTO(team));
    }

    // PUT by ID
    @PutMapping("/slot/{id}")
    public ResponseEntity<TeamResponseDTO> updateTeamByID(@PathVariable Long id,
            @RequestBody CreateAndUpdateTeamDTO dto) {
        try {
            Team updatedTeam = teamService.updateTeamByID(id, dto);
            return ResponseEntity.ok(teamService.convertToDTO(updatedTeam));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Put by ID
    @PutMapping("/{id}")
    public ResponseEntity<TeamResponseDTO> updateTeamSlot(@PathVariable Long id,
            @RequestBody TeamUpdateSlotDTO dto) {
        try {
            Team team = teamService.getTeamByID(id);
            teamService.updateAnimal(team, dto.getIndex(), dto.getTeamAnimalDTO());
            teamService.save(team);
            return ResponseEntity.ok(teamService.convertToDTO(team));
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
