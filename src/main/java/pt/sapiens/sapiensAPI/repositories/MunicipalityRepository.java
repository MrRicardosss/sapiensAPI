package pt.sapiens.sapiensAPI.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.sapiens.sapiensAPI.entities.Municipality;

public interface MunicipalityRepository extends CrudRepository<Municipality, Long> {
}
