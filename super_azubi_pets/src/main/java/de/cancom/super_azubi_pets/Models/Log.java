package de.cancom.super_azubi_pets.Models;

import de.cancom.super_azubi_pets.EmbeddedIds.LogID;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "fight_log")
public class Log {

    @EmbeddedId
    private LogID logID;

    @Column(name = "log", columnDefinition = "TEXT")
    private String log;

    public Log() {
    }

    public Log(LogID logID, String log) {
        this.logID = logID;
        this.log = log;
    }

    public LogID getLogID() {
        return logID;
    }

    public void setLogID(LogID logID) {
        this.logID = logID;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

}
