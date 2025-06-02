package de.cancom.super_azubi_pets.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.cancom.super_azubi_pets.DTOs.CreateAndUpdateFightDTO;
import de.cancom.super_azubi_pets.Models.Fight;
import de.cancom.super_azubi_pets.Models.Team;
import de.cancom.super_azubi_pets.Repositories.FightRepository;
import de.cancom.super_azubi_pets.Repositories.TeamRepository;

@Service
public class FightService {

    @Autowired
    private TeamRepository teamRepo;

    @Autowired
    private FightRepository fightRepo;

    // Create
    public Fight CreateAndUpdateFight(CreateAndUpdateFightDTO dto) {
        Team team1 = teamRepo.findById(dto.getTeam1Id())
                .orElseThrow(() -> new RuntimeException("Team 1 not found"));

        Team team2 = teamRepo.findById(dto.getTeam2Id())
                .orElseThrow(() -> new RuntimeException("Team 2 not found"));

        Fight fight = new Fight();
        fight.setTeam1(team1);
        fight.setTeam2(team2);

        return fightRepo.save(fight);
    }

    // Read
    public List<Fight> getAllFights() {
        return fightRepo.findAll();
    }

    // Read
    public Optional<Fight> getFightById(Long Id) {
        return fightRepo.findById(Id);
    }

    // Update
    public Fight updateFight(Long id, CreateAndUpdateFightDTO dto) {
        Fight fight = fightRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Fight with ID " + id + " not found."));

        Team team1 = teamRepo.findById(dto.getTeam1Id())
                .orElseThrow(() -> new RuntimeException("Team 1 with ID " + dto.getTeam1Id() + " not found."));

        Team team2 = teamRepo.findById(dto.getTeam2Id())
                .orElseThrow(() -> new RuntimeException("Team 2 with ID " + dto.getTeam2Id() + " not found."));

        fight.setTeam1(team1);
        fight.setTeam2(team2);

        return fightRepo.save(fight);
    }

    // Delete
    public void deleteFight(Long id) {
        if (fightRepo.existsById(id)) {
            fightRepo.deleteById(id);
        } else {
            throw new RuntimeException("Fight with ID " + id + " not found.");
        }
    }
}
