package de.cancom.super_azubi_pets.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.cancom.super_azubi_pets.Models.Log;
import de.cancom.super_azubi_pets.EmbeddedIds.LogID;

/**
 * Methods granted automatically:
 * findAll()
 * findById(LogID id)
 * save(Team team) // creates or updates an object
 * deleteById(LogID id)
 * existsById(LogID id) //checks if object exists
 * count() //returns how many objects of this type are in the table
 */
@Repository
public interface LogRepository extends JpaRepository<Log, LogID> {

}
