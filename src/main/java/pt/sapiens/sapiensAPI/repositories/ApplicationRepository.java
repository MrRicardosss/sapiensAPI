package pt.sapiens.sapiensAPI.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.sapiens.sapiensAPI.entities.Application;

import java.util.List;

public interface ApplicationRepository extends CrudRepository<Application, Long> {

    List<Application> findByOfferId(long id);
}
