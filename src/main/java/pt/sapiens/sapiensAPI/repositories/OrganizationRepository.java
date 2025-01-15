package pt.sapiens.sapiensAPI.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.sapiens.sapiensAPI.entities.Organization;

import java.util.Optional;

public interface OrganizationRepository extends CrudRepository<Organization, Long> {
    Optional<Organization> findByUserId(Long id);
}
