package de.cancom.super_azubi_pets.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Repositories.TeamAnimalRepository;
import de.cancom.super_azubi_pets.Services.TeamAnimalService;
import de.cancom.super_azubi_pets.DTOs.CreateAndUpdateTeamAnimalDTO;

@RestController
@RequestMapping("/teamanimal")
public class TeamAnimalController {

    @Autowired
    private TeamAnimalService teamAnimalService;

    @Autowired
    private TeamAnimalRepository teamAnimalRepository;

    // Create
    @PostMapping
    public ResponseEntity<TeamAnimal> createTeamAnimal(@RequestBody CreateAndUpdateTeamAnimalDTO dto) {
        TeamAnimal newTeamAnimal = teamAnimalService.createTeamAnimal(dto);
        return ResponseEntity.ok(newTeamAnimal);
    }

    // Get
    @GetMapping("/{id}")
    public ResponseEntity<TeamAnimal> getAnimal(@PathVariable Long id) {
        return new ResponseEntity<TeamAnimal>(teamAnimalRepository.findById(id)
                .orElse(null), HttpStatus.OK);
    }

}
