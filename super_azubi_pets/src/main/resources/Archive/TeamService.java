package Archive;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.cancom.super_azubi_pets.Models.TeamAnimal;
import de.cancom.super_azubi_pets.Repositories.TeamAnimalRepository;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepo;

    @Autowired
    private TeamAnimalRepository teamAnimalRepository;

    // Read
    public List<Team> getAllTeams() {
        return teamRepo.findAll();
    }

    // Read
    public Optional<Team> getTeamById(Long Id) {
        return teamRepo.findById(Id);
    }

    // Create
    public Team addTeam(CreateAndUpdateTeamDTO dto) {
        try {
            Team newTeam = new Team(dto.getHearts(), dto.getWins(), dto.getRounds());
            for (TeamAnimal newTeamAnimal : dto.getAnimals()) {
                teamAnimalRepository.save(newTeamAnimal);
            }
            return teamRepo.save(newTeam);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not save team", e);
        }
    }

    // Update
    public Team updateTeam(Long id, CreateAndUpdateTeamDTO dto) {
        Optional<Team> optionalTeam = teamRepo.findById(id);

        if (optionalTeam.isPresent()) {
            Team existingTeam = optionalTeam.get();
            existingTeam.setHearts(dto.getHearts());
            existingTeam.setWins(dto.getWins());
            existingTeam.setRounds(dto.getRounds());
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
