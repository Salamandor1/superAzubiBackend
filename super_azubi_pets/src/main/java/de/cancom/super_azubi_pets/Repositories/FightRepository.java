package de.cancom.super_azubi_pets.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.cancom.super_azubi_pets.Models.Fight;

/**
 * Methods granted automatically:
 * findAll()
 * findById(Long id)
 * save(Team team) // creates or updates an object
 * deleteById(Long id)
 * existsById(Long id) //checks if object exists
 * count() //returns how many objects of this type are in the table
 */

public interface FightRepository extends JpaRepository<Fight, Long> {

}
