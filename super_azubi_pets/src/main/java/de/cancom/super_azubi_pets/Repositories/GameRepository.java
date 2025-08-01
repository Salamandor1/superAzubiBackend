package de.cancom.super_azubi_pets.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import de.cancom.super_azubi_pets.Models.Game;
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
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query(value = "SELECT * FROM game WHERE game_id != :id AND rounds = :rounds AND hearts BETWEEN :heartsMin AND :heartsMax AND wins BETWEEN :winsMin AND :winsMax ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Optional<Game> findGameByStatus(@Param("id") long id, @Param("heartsMin") int heartsMin,
            @Param("heartsMax") int heartsMax, @Param("rounds") int rounds, @Param("winsMin") int winsMin,
            @Param("winsMax") int winsMax);

    @Transactional
    @Modifying
    @Query(value = "ALTER SEQUENCE public.game_game_id_seq RESTART WITH 1", nativeQuery = true)
    void resetIDSequence();

}
