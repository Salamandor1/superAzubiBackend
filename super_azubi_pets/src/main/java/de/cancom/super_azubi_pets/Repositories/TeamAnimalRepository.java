package de.cancom.super_azubi_pets.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import de.cancom.super_azubi_pets.Models.TeamAnimal;
import jakarta.transaction.Transactional;

/**
 * Methods granted automatically:
 * findAll()
 * findById(Long id)
 * save(Team team) // creates or updates an object
 * deleteById(Long id)
 * existsById(Long id) //checks if object exists
 * count() //returns how many objects of this type are in the table
 */
@Repository
public interface TeamAnimalRepository extends JpaRepository<TeamAnimal, Long> {

    @Transactional
    @Modifying
    @Query(value = "ALTER SEQUENCE public.team_animals_team_animal_id_seq RESTART WITH 1", nativeQuery = true)
    void resetIDSequence();

}
