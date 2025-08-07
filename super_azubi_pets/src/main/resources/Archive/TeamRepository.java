package Archive;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
public interface TeamRepository extends JpaRepository<Team, Long> {

}
