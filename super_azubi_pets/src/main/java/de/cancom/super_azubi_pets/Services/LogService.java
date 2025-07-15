package de.cancom.super_azubi_pets.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import de.cancom.super_azubi_pets.DTOs.LogQueryDTO;
import de.cancom.super_azubi_pets.DTOs.LogResponseDTO;
import de.cancom.super_azubi_pets.EmbeddedIds.LogID;
import de.cancom.super_azubi_pets.Models.Log;
import de.cancom.super_azubi_pets.Repositories.LogRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepo;

    // Find
    public LogResponseDTO getLogByID(LogQueryDTO dto) {
        LogID logID = new LogID(dto.getPlayerTeamID(), dto.getNpcTeamID());
        Log log = logRepo.findById(logID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Log nicht gefunden."));
        return new LogResponseDTO(log.getLog());
    }

    // Create
    public LogResponseDTO saveLog(LogQueryDTO dto, String log) {
        LogID logID = new LogID(dto.getPlayerTeamID(), dto.getNpcTeamID());

        if (logRepo.existsById(logID)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Log existiert bereits.");
        }

        Log logObject = new Log(logID, log);
        logRepo.save(logObject);

        LogResponseDTO logResponse = new LogResponseDTO(log);

        return logResponse;
    }

    // Delete
    public void deleteLogByID(LogID id) {
        if (logRepo.existsById(id)) {
            logRepo.deleteById(id);
        } else
            throw new EntityNotFoundException("Log with ID " + id + " not found.");
    }

}
