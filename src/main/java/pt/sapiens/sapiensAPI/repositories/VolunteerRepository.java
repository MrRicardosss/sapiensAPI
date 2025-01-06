package pt.sapiens.sapiensAPI.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.sapiens.sapiensAPI.entities.Volunteer;

@Repository
public interface VolunteerRepository extends CrudRepository<Volunteer, Long> {
}
