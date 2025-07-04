package de.cancom.super_azubi_pets.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.cancom.super_azubi_pets.Models.Animal;

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
public interface AnimalRepository extends JpaRepository<Animal, String> {
    // Additional custom query methods can be defined here if needed
    // For example, to find an animal by its name
}
