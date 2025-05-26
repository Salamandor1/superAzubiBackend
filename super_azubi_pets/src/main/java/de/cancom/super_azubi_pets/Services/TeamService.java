package de.cancom.super_azubi_pets.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.cancom.super_azubi_pets.Models.Team;
import de.cancom.super_azubi_pets.Repositories.TeamRepository;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepo;

    // Read
    public List<Team> getAllTeams() {
        return teamRepo.findAll();
    }

    // Read
    public Optional<Team> getTeambyId(Long Id) {
        return teamRepo.findById(Id);
    }

    // Create
    public Team addTeam(Team team) {
        try {
            return teamRepo.save(team);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not save team", e);
        }
    }

    // Update
    public Team updateTeam(Long id, Team updatedData) {
        Optional<Team> optionalTeam = teamRepo.findById(id);

        if (optionalTeam.isPresent()) {
            Team existingTeam = optionalTeam.get();

            existingTeam.setHearts(updatedData.getHearts());
            existingTeam.setWins(updatedData.getWins());
            existingTeam.setRounds(updatedData.getRounds());

            return teamRepo.save(existingTeam);
        } else {
            throw new RuntimeException("Team with ID " + id + " not found.");
        }
    }

    // Delete
    public void deleteTeam(Long id) {
        if (teamRepo.existsById(id)) {
            teamRepo.deleteById(id);
        } else {
            throw new RuntimeException("Team with ID " + id + " not found.");
        }
    }
}
