package de.cancom.super_azubi_pets.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query(value = "SELECT * FROM animals ORDER BY RANDOM() LIMIT :limit", nativeQuery = true)
    List<Animal> findRandomAnimals(@Param("limit") int limit);

    @Query(value = "SELECT * FROM animals ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Animal findRandomAnimal();

}
