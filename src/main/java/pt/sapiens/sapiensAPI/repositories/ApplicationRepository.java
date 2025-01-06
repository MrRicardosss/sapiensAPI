package pt.sapiens.sapiensAPI.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.sapiens.sapiensAPI.entities.Application;

public interface ApplicationRepository extends CrudRepository<Application, Long> {
}
