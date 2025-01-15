package pt.sapiens.sapiensAPI.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.sapiens.sapiensAPI.entities.Volunteer;

import java.util.Optional;

@Repository
public interface VolunteerRepository extends CrudRepository<Volunteer, Long> {
    Optional<Volunteer> findByUserId(Long id);
}
