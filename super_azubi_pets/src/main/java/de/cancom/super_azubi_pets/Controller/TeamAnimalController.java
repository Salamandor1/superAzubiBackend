package de.cancom.super_azubi_pets.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.cancom.super_azubi_pets.DTOs.TeamAnimalCreateDTO;
import de.cancom.super_azubi_pets.DTOs.TeamAnimalResponseDTO;
import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Services.TeamAnimalService;

@RestController
@RequestMapping("/teamanimal")
public class TeamAnimalController {

    @Autowired
    private TeamAnimalService teamAnimalService;

    // Create
    @PostMapping
    public ResponseEntity<TeamAnimalResponseDTO> createTeamAnimal(@RequestBody TeamAnimalCreateDTO dto) {
        TeamAnimal newTeamAnimal = teamAnimalService.createTeamAnimal(dto);
        return ResponseEntity.ok(teamAnimalService.convertToDTO(newTeamAnimal));
    }

    // Get
    @GetMapping("/{id}")
    public ResponseEntity<TeamAnimalResponseDTO> getTeamAnimalByID(@PathVariable Long id) {
        TeamAnimal teamAnimal = teamAnimalService.getTeamAnimalById(id);
        return ResponseEntity.ok(teamAnimalService.convertToDTO(teamAnimal));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeamAnimalByID(@PathVariable Long id) {
        teamAnimalService.deleteTeamAnimalByID(id);
        return ResponseEntity.ok("TeamAnimal with ID " + id + " was deleted.");
    }

}
